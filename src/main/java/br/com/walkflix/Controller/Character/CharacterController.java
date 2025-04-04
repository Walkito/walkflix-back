package br.com.walkflix.Controller.Character;

import br.com.walkflix.Model.ApiResponse;
import br.com.walkflix.Model.Entitie.Character.Character;
import br.com.walkflix.Model.DTO.ImageDTO;
import br.com.walkflix.Service.Character.CharacterService;
import br.com.walkflix.Service.Image.ImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/character")
public class CharacterController {
    @Autowired
    private CharacterService characterService;

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

        return characterService.saveFilePath(id, filePath);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createCharacter(@RequestBody @Valid Character character){
        return characterService.createCharacter(character);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> editCharacter(@RequestParam(name = "id") int id,
                                                     @RequestBody @Valid Character character){
        return characterService.editCharacter(id, character);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteCharacter(@RequestParam(name = "id") int id){
        return characterService.deleteCharacter(id);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getCharacter(@RequestParam(name = "id") int id){
        return characterService.getCharacter(id);
    }

    @GetMapping(path = "/series")
    public ResponseEntity<ApiResponse> getAllCharacterBySeries(@RequestParam(name = "idSeries") int idSeries){
        return characterService.getAllCharacterBySeries(idSeries);
    }
}
