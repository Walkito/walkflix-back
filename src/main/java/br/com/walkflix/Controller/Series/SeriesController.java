package br.com.walkflix.Controller.Series;

import br.com.walkflix.Model.ApiResponse;
import br.com.walkflix.Model.DTO.Series.SeriesDTO;
import br.com.walkflix.Model.Entitie.Series.Series;
import br.com.walkflix.Model.DTO.ImageDTO;
import br.com.walkflix.Service.Image.ImageService;
import br.com.walkflix.Service.Series.SeriesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/series")
public class SeriesController {
    @Autowired
    private SeriesService seriesService;

    @Autowired
    private ImageService imageService;

    @PostMapping(path = "/upload")
    public ResponseEntity<ApiResponse> uploadActorPicture(@RequestParam(name = "path") String path,
                                                          @RequestParam(name = "id") int id,
                                                          @RequestBody ImageDTO imageDTO,
                                                          @RequestParam(name = "option") String option){
        String filePath = imageService.uploadImage(path, imageDTO);

        if(filePath.isEmpty()){
            return ResponseEntity.badRequest().body(new ApiResponse(
                    "Não foi possivel salvar o arquivo: Já existe outro arquivo com este mesmo nome!",
                    null,
                    HttpStatus.BAD_REQUEST.value()
            ));
        }

        return seriesService.saveFilePath(id, filePath, option);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createSeries(@RequestBody @Valid SeriesDTO series){
        return seriesService.createSeries(series);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> editSeries(@RequestParam(name = "id") int id,
                                                  @RequestBody @Valid SeriesDTO series){
        return seriesService.editSeries(id, series);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteSeries(@RequestParam(name = "id") int id){
        return seriesService.deleteSeries(id);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getSeries(@RequestParam(name = "id") int id){
        return seriesService.getSeries(id);
    }

    @GetMapping(path = "/filter")
    public ResponseEntity<ApiResponse> getSeriesWithFilter(@RequestParam(name = "id") int id,
                                                           @RequestParam(name = "seriesName") String seriesName,
                                                           @RequestParam(name = "directors")List<Integer> directorsId){
        return seriesService.findSeriesWithFilter(id, seriesName, directorsId);
    }
}
