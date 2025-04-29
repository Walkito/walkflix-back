package br.com.walkflix.Controller.Series.Episode;

import br.com.walkflix.Model.ApiResponse;
import br.com.walkflix.Model.DTO.Series.Episode.EpisodeDTO;
import br.com.walkflix.Model.Entitie.Series.Episode.Episode;
import br.com.walkflix.Model.DTO.ImageDTO;
import br.com.walkflix.Service.Image.ImageService;
import br.com.walkflix.Service.Series.Episode.EpisodeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/episode")
public class EpisodeController {
    @Autowired
    private EpisodeService episodeService;

    @Autowired
    private ImageService imageService;

    @PostMapping(path = "/upload")
    public ResponseEntity<ApiResponse> uploadEpisodePicture(@RequestParam(name = "path") String path,
                                                          @RequestParam(name = "id") int id,
                                                          @RequestBody ImageDTO imageDTO){
        String filePath = imageService.uploadImage(path, imageDTO);

        if(filePath.isEmpty()){
            return ResponseEntity.badRequest().body(new ApiResponse(
                    "Não foi possivel salvar o arquivo: Já existe outro arquivo com este mesmo nome!",
                    null,
                    HttpStatus.BAD_REQUEST.value()
            ));
        }

        return episodeService.saveFilePath(id, filePath);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createEpisode(@RequestBody @Valid EpisodeDTO episodeDTO){
        return episodeService.createEpisode(episodeDTO);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> editEpisode(@RequestParam(name = "id") int id,
                                                   @RequestBody @Valid EpisodeDTO episodeDTO){
        return  episodeService.editEpisode(id, episodeDTO);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteEpisode(@RequestParam(name = "id") int id){
        return episodeService.deleteEpisode(id);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getEpisode(@RequestParam(name = "id") int id){
        return episodeService.getEpisode(id);
    }

    @GetMapping(path = "/series")
    public ResponseEntity<ApiResponse> getAllSeriesEpisode(@RequestParam(name = "idSeries") int idSeries){
        return episodeService.getAllSeriesEpisode(idSeries);
    }
}
