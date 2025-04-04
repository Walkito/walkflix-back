package br.com.walkflix.Controller.Actor;

import br.com.walkflix.Model.ApiResponse;
import br.com.walkflix.Model.Entitie.Actor.Actor;
import br.com.walkflix.Model.DTO.ImageDTO;
import br.com.walkflix.Service.Actor.ActorService;
import br.com.walkflix.Service.Image.ImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
                                                          @RequestBody ImageDTO imageDTO){
        String filePath = imageService.uploadImage(path, imageDTO);

        if(filePath.isEmpty()){
            return ResponseEntity.badRequest().body(new ApiResponse(
                    "Não foi possivel salvar o arquivo: Já existe outro arquivo com este mesmo nome!",
                    null,
                    HttpStatus.BAD_REQUEST.value()
            ));
        }

        return actorService.saveFilePath(id, filePath);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createActor(@RequestBody @Valid Actor actor){
        return actorService.createActor(actor);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> editActor(@RequestParam(name = "id") int id,
                                                 @RequestBody @Valid Actor actor){
        return actorService.editActor(id, actor);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteActor(@RequestParam(name = "id") int id){
        return actorService.deleteActor(id);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getActor(@RequestParam(name = "id") int id){
        return actorService.getActor(id);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<ApiResponse> getAllActor(){
        return actorService.getAllActors();
    }
}
