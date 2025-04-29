package br.com.walkflix.Controller.Actor;

import br.com.walkflix.Model.ApiResponse;
import br.com.walkflix.Model.DTO.Actor.ActorDTO;
import br.com.walkflix.Model.DTO.ImageDTO;
import br.com.walkflix.Service.Actor.ActorService;
import br.com.walkflix.Service.Image.ImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/actor")
@Validated
public class ActorController {
    @Autowired
    private ActorService actorService;

    @Autowired
    private ImageService imageService;

    @PostMapping(path = "/upload")
    public ResponseEntity<ApiResponse> uploadActorPicture(@RequestParam(name = "path") String path,
                                                          @RequestParam(name = "id") int id,
                                                          @RequestBody ImageDTO imageDTO) {
        String filePath = imageService.uploadImage(path, imageDTO);

        if (filePath.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse(
                    "Não foi possivel salvar o arquivo: Já existe outro arquivo com este mesmo nome!",
                    null,
                    HttpStatus.BAD_REQUEST.value()
            ));
        }

        return actorService.saveFilePath(id, filePath);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createActor(@RequestBody @Valid ActorDTO actorDTO) {
        return actorService.createActor(actorDTO);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> editActor(@RequestParam(name = "id") int id,
                                                 @RequestBody @Valid ActorDTO actorDTO) {
        return actorService.editActor(id, actorDTO);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteActor(@RequestParam(name = "id") int id) {
        return actorService.deleteActor(id);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getActor(@RequestParam(name = "id") int id,
                                                @RequestParam(name = "txActorName") String txActorName,
                                                @RequestParam(name = "series") List<Integer> series) {
        return actorService.getActor(id, txActorName, series);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<ApiResponse> getAllActor() {
        return actorService.getAllActors();
    }

    @GetMapping(path = "/directors")
    public ResponseEntity<ApiResponse> getAllDirectors() {
        return actorService.getAllDirectors();
    }

    @GetMapping(path = "/actorSeries")
    public ResponseEntity<ApiResponse> getActorSeries(@RequestParam(name = "id") int id) {
        return actorService.getActorSeries(id);
    }
}
