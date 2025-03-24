package br.com.walkflix.Service.Series.Episode;

import br.com.walkflix.Model.ApiResponse;
import br.com.walkflix.Model.Entitie.Series.Episode.Episode;
import br.com.walkflix.Model.Entitie.Series.Episode.EpisodeRepository;
import br.com.walkflix.Utils.DefaultErroMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class EpisodeService {
    @Autowired
    private EpisodeRepository episodeRepository;

    public ResponseEntity<ApiResponse> saveFilePath(int id, String filePath){
        try{
            return episodeRepository.findById(id).map(episode -> {
                episode.setTxEpisodePicture(filePath);
                episodeRepository.save(episode);

                return ResponseEntity.ok(new ApiResponse(
                        "Arquivo salvo com sucesso!",
                        episode,
                        HttpStatus.OK.value()
                ));
            }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(
                    "Não foi possível salvar o caminho do arquivo: Episódio não encontrado.",
                    null,
                    HttpStatus.NOT_FOUND.value()
            )));
        } catch(Exception e){
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> createEpisode(Episode episode) {
        try {
            return ResponseEntity.created(URI.create("/episode")).body(
                    new ApiResponse(
                            "Episódio criado com sucesso!",
                            episodeRepository.save(episode),
                            HttpStatus.CREATED.value()
                    )
            );
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> editEpisode(int id, Episode episode) {
        try {
            episode.setId(id);

            return ResponseEntity.ok(new ApiResponse(
                    "Episódio editado com sucesos!",
                    episodeRepository.save(episode),
                    HttpStatus.OK.value()
            ));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> deleteEpisode(int id) {
        try {
            return episodeRepository.findById(id).map(
                    episode -> {
                        episodeRepository.delete(episode);

                        return ResponseEntity.ok(new ApiResponse(
                                "Episódio excluído com sucesso!",
                                null,
                                HttpStatus.OK.value()
                        ));
                    }
            ).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(
                    "Não foi possível excluir o episódio: Episódio não encontrado!",
                    null,
                    HttpStatus.NOT_FOUND.value()
            )));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> getEpisode(int id) {
        try {
            return episodeRepository.findById(id).map(
                    episode -> {
                        return ResponseEntity.ok(new ApiResponse(
                                "Episódio encontrado com sucesso!",
                                episode,
                                HttpStatus.OK.value()
                        ));
                    }
            ).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(
                    "Episódio não econtrado.",
                    null,
                    HttpStatus.NOT_FOUND.value()
            )));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> getAllSeriesEpisode(int idSeries) {
        try {
            List<Episode> episodes = episodeRepository.findAllBySeriesId(idSeries);

            if (!episodes.isEmpty()) {
                return ResponseEntity.ok(new ApiResponse(
                        "Episódios encontrados com sucesso!",
                        episodes,
                        HttpStatus.OK.value()
                ));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(
                        "Episódios não encontrados!",
                        null,
                        HttpStatus.NOT_FOUND.value()
                ));
            }
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }
}
