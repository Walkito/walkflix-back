package br.com.walkflix.Controller.ImageController;

import br.com.walkflix.Model.ApiResponse;
import br.com.walkflix.Service.Image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping
    public ResponseEntity<?> downloadFile(@RequestParam(name = "path") String path){
        return imageService.downloadFile(path);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteFile(@RequestParam(name = "path") String path){
        return imageService.deleteFile(path);
    }
}
