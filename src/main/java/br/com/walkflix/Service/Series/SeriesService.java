package br.com.walkflix.Service.Series;

import br.com.walkflix.Config.MapperUtil;
import br.com.walkflix.Model.ApiResponse;
import br.com.walkflix.Model.DTO.Series.ActorForSeriesResponseDTO;
import br.com.walkflix.Model.DTO.Series.SeriesDefaultDTO;
import br.com.walkflix.Model.Entitie.Actor.Actor;
import br.com.walkflix.Model.Entitie.Series.Series;
import br.com.walkflix.Model.Entitie.Series.SeriesRepository;
import br.com.walkflix.Model.Entitie.Series.SeriesSpecification;
import br.com.walkflix.Utils.DefaultErroMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeriesService {
    @Autowired
    private SeriesRepository seriesRepository;

    public ResponseEntity<ApiResponse> saveFilePath(int id, String filePath, String option){
        try{
            return seriesRepository.findById(id).map(series -> {
                if (option.equals("Poster")){
                    series.setTxPicturePoster(filePath);
                } else if(option.equals("Banner"))  {
                    series.setTxPictureBanner(filePath);
                } else {
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
        } catch(Exception e){
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> createSeries(Series series) {
        try {
            return ResponseEntity.created(URI.create("/series")).body(new ApiResponse(
                    "Série criada com sucesso.",
                    seriesRepository.save(series),
                    HttpStatus.OK.value()
            ));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> editSeries(int id, Series series) {
        try {
            series.setId(id);

            return ResponseEntity.ok(new ApiResponse(
                    "Série editada com sucesso.",
                    seriesRepository.save(series),
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
                    series -> {
                        return ResponseEntity.ok(new ApiResponse(
                                "Série encontrada com sucesso!",
                                series,
                                HttpStatus.OK.value()
                        ));
                    }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(
                    "Série não encontrada!",
                    null,
                    HttpStatus.NOT_FOUND.value()
            )));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> findSeriesWithFilter(int id, String seriesName, List<Integer> directorsId, int dto){
        try{
            Specification<Series> spec = SeriesSpecification.filterSeries(id, seriesName, directorsId);
            List<Series> series = seriesRepository.findAll(spec);
            List response = new ArrayList<>();

            switch (dto){
                case 0:
                    for(Series s : series){
                        SeriesDefaultDTO seriesDTO = MapperUtil.convertToDTO(s, SeriesDefaultDTO.class);
                        seriesDTO.setDirector(MapperUtil.convertToDTO(s.getDirector(), ActorForSeriesResponseDTO.class));
                        response.add(seriesDTO);
                    }
                    break;
            }

            if(!series.isEmpty()){
                return ResponseEntity.ok(new ApiResponse(
                        "Séries encontradas com sucesso!",
                        response,
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
        } catch(Exception e){
            return DefaultErroMessage.getDefaultError(e);
        }
    }


}
