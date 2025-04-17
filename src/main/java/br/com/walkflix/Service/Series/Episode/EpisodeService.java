package br.com.walkflix.Service.Series.Episode;

import br.com.walkflix.Config.MapperUtil;
import br.com.walkflix.Model.ApiResponse;
import br.com.walkflix.Model.DTO.Series.Episode.EpisodeDTO;
import br.com.walkflix.Model.Entitie.Series.Episode.Episode;
import br.com.walkflix.Model.Entitie.Series.Episode.EpisodeRepository;
import br.com.walkflix.Model.Entitie.Series.Series;
import br.com.walkflix.Model.Entitie.Series.SeriesRepository;
import br.com.walkflix.Service.Image.ImageService;
import br.com.walkflix.Utils.DefaultErroMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Service
public class EpisodeService {
    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private ImageService imageService;

    public ResponseEntity<ApiResponse> saveFilePath(int id, String filePath) {
        try {
            return episodeRepository.findById(id).map(episode -> {
                imageService.deleteFile(episode.getTxEpisodePicture());
                episode.setTxEpisodePicture(filePath);
                episodeRepository.save(episode);

                return ResponseEntity.ok(new ApiResponse(
                        "Arquivo salvo com sucesso!",
                        MapperUtil.convert(episode, EpisodeDTO.class),
                        HttpStatus.OK.value()
                ));
            }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(
                    "Não foi possível salvar o caminho do arquivo: Episódio não encontrado.",
                    null,
                    HttpStatus.NOT_FOUND.value()
            )));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> createEpisode(EpisodeDTO episodeDTO) {
        try {
            Episode episode = MapperUtil.convert(episodeDTO, Episode.class);
            Series series = seriesRepository.getReferenceById(episodeDTO.getIdSeries());
            episode.setSeries(series);
            EpisodeDTO episodeResponse = MapperUtil.convert(episodeRepository.save(episode), EpisodeDTO.class);

            return ResponseEntity.created(URI.create("/episode")).body(
                    new ApiResponse(
                            "Episódio criado com sucesso!",
                            episodeResponse,
                            HttpStatus.CREATED.value()
                    )
            );
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> editEpisode(int id, EpisodeDTO episodeDTO) {
        try {
            episodeDTO.setId(id);
            Episode episode = MapperUtil.convert(episodeDTO, Episode.class);
            EpisodeDTO episodeResponse = MapperUtil.convert(episodeRepository.save(episode), EpisodeDTO.class);

            return ResponseEntity.ok(new ApiResponse(
                    "Episódio editado com sucesos!",
                    episodeResponse,
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
                    episode -> ResponseEntity.ok(new ApiResponse(
                            "Episódio encontrado com sucesso!",
                            MapperUtil.convert(episode, EpisodeDTO.class),
                            HttpStatus.OK.value()
                    ))
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
            List<EpisodeDTO> episodesDTOs = episodeRepository.findAllBySeriesId(idSeries)
                    .stream()
                    .map(episode -> MapperUtil.convert(episode, EpisodeDTO.class))
                    .toList();

            if (!episodesDTOs.isEmpty()) {
                return ResponseEntity.ok(new ApiResponse(
                        "Episódios encontrados com sucesso!",
                        episodesDTOs,
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
