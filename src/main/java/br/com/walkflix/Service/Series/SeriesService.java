package br.com.walkflix.Service.Series;

import br.com.walkflix.Config.MapperUtil;
import br.com.walkflix.Model.ApiResponse;
import br.com.walkflix.Model.DTO.Series.SeriesDTO;
import br.com.walkflix.Model.Entitie.Actor.ActorRepository;
import br.com.walkflix.Model.Entitie.Series.Series;
import br.com.walkflix.Model.Entitie.Series.SeriesRepository;
import br.com.walkflix.Model.Entitie.Series.SeriesSpecification;
import br.com.walkflix.Service.Image.ImageService;
import br.com.walkflix.Utils.DefaultErroMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class SeriesService {
    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ImageService imageService;

    public ResponseEntity<ApiResponse> saveFilePath(int id, String filePath, String option) {
        try {
            return seriesRepository.findById(id).map(series -> {
                if (option.equals("Poster")) {
                    imageService.deleteFile(series.getTxPicturePoster());
                    series.setTxPicturePoster(filePath);
                } else if (option.equals("Banner")) {
                    imageService.deleteFile(series.getTxPictureBanner());
                    series.setTxPictureBanner(filePath);
                } else {
                    imageService.deleteFile(series.getTxPictureThumbnail());
                    series.setTxPictureThumbnail(filePath);
                }

                seriesRepository.save(series);

                return ResponseEntity.ok(new ApiResponse(
                        "Arquivo salvo com sucesso!",
                        series,
                        HttpStatus.OK.value()
                ));
            }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(
                    "Não foi possível salvar o caminho do arquivo: Série não encontrada.",
                    null,
                    HttpStatus.NOT_FOUND.value()
            )));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> createSeries(SeriesDTO seriesDTO) {
        try {
            Series series = MapperUtil.convert(seriesDTO, Series.class);
            seriesRepository.save(series);

            SeriesDTO newSeries = MapperUtil.convert(series, SeriesDTO.class);
            return ResponseEntity.created(URI.create("/series")).body(new ApiResponse(
                    "Série criada com sucesso.",
                    newSeries,
                    HttpStatus.OK.value()
            ));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> editSeries(int id, SeriesDTO seriesDTO) {
        try {
            seriesDTO.setId(id);

            Series series = MapperUtil.convert(seriesDTO, Series.class);
            SeriesDTO seriesResponse = MapperUtil.convert(seriesRepository.save(series), SeriesDTO.class);

            return ResponseEntity.ok(new ApiResponse(
                    "Série editada com sucesso.",
                    seriesResponse,
                    HttpStatus.OK.value()
            ));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> deleteSeries(int id) {
        try {
            return seriesRepository.findById(id).map(
                    series -> {
                        seriesRepository.delete(series);

                        return ResponseEntity.ok(new ApiResponse(
                                "Série deletada com sucesso.",
                                null,
                                HttpStatus.OK.value()
                        ));
                    }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(
                            "Não foi possível excluir a série: Série não encontrada.",
                            null,
                            HttpStatus.NOT_FOUND.value()
                    )
            ));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> getSeries(int id) {
        try {
            return seriesRepository.findById(id).map(
                    series -> ResponseEntity.ok(new ApiResponse(
                            "Série encontrada com sucesso!",
                            MapperUtil.convert(series, SeriesDTO.class),
                            HttpStatus.OK.value()
                    ))).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(
                    "Série não encontrada!",
                    null,
                    HttpStatus.NOT_FOUND.value()
            )));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> findSeriesWithFilter(int id, String seriesName, List<Integer> directorsId) {
        try {
            Specification<Series> spec = SeriesSpecification.filterSeries(id, seriesName, directorsId);
            List<Series> series = seriesRepository.findAll(spec);
            List<SeriesDTO> seriesResponse = series.stream().map(s -> MapperUtil.convert(s, SeriesDTO.class)).toList();

            if (!series.isEmpty()) {
                return ResponseEntity.ok(new ApiResponse(
                        "Séries encontradas com sucesso!",
                        seriesResponse,
                        HttpStatus.OK.value()
                ));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse(
                                "Não foi possível buscar as séries!",
                                null,
                                HttpStatus.NOT_FOUND.value()
                        )
                );
            }
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }
}
