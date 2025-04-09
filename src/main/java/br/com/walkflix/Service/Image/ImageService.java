package br.com.walkflix.Service.Image;

import br.com.walkflix.Config.S3Config;
import br.com.walkflix.Model.ApiResponse;
import br.com.walkflix.Model.DTO.ImageDTO;
import br.com.walkflix.Utils.DefaultErroMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImageService {

    private final S3Client s3Client;
    private final S3Config s3Config;


    public ImageService(S3Client s3Client, S3Config s3Config) {
        this.s3Client = s3Client;
        this.s3Config = s3Config;
    }

    public String uploadImage(String path, ImageDTO imageDTO) {
        try {
            String fileName = path + imageDTO.getFileName();

            if (checkExistingFile(fileName)) {
                return "";
            }

            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(s3Config.getBucketName())
                            .key(fileName)
                            .build(),
                    transformFile(imageDTO)
            );

            return fileName;
        } catch (S3Exception e) {
            return "Erro: " + e.getMessage();
        }
    }

    public ResponseEntity<?> downloadFile(String path) {
        try {
            path = path.isEmpty() || path.isBlank() ? "caminhoInexistente" : path;

            ResponseInputStream<GetObjectResponse> s3Object = s3Client.getObject(
                    GetObjectRequest.builder()
                            .bucket(s3Config.getBucketName())
                            .key(path)
                            .build()
            );

            byte[] content = s3Object.readAllBytes();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(s3Object.response().contentType()));

            return ResponseEntity.ok().headers(headers).body(content);
        } catch (NoSuchKeyException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(
                    "Não foi possível buscar a imagem: Caminho não encontrado.",
                    null,
                    HttpStatus.NOT_FOUND.value()
            ));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> deleteFile(String path) {
        try {
            s3Client.deleteObject(DeleteObjectRequest.builder()
                    .bucket(s3Config.getBucketName())
                    .key(path)
                    .build());

            return ResponseEntity.ok(new ApiResponse(
                    "Arquivo excluído com sucesso!",
                    null,
                    HttpStatus.OK.value()
            ));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    private RequestBody transformFile(ImageDTO image) {
        byte[] decodedBytes = decodeBase64(image.getImageB64());

        ByteBuffer byteBuffer = ByteBuffer.wrap(decodedBytes);
        return RequestBody.fromByteBuffer(byteBuffer);
    }

    private byte[] decodeBase64(String imageInBase64) {
        try {
            // Verifique se a string contém "data:image/..."
            if (imageInBase64.startsWith("data:image")) {
                imageInBase64 = imageInBase64.substring(imageInBase64.indexOf(",") + 1); // Remove o cabeçalho
            }

            return Base64.getDecoder().decode(imageInBase64);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erro ao decodificar Base64: " + e.getMessage());
        }
    }

    public boolean checkExistingFile(String path) {
        ResponseEntity<?> obj = downloadFile(path);
        return obj.getStatusCode() == HttpStatus.OK;
    }
}
