package br.com.walkflix.Service.Character;

import br.com.walkflix.Config.MapperUtil;
import br.com.walkflix.Model.ApiResponse;
import br.com.walkflix.Model.DTO.Character.CharacterDTO;
import br.com.walkflix.Model.Entitie.Actor.Actor;
import br.com.walkflix.Model.Entitie.Actor.ActorRepository;
import br.com.walkflix.Model.Entitie.Character.Character;
import br.com.walkflix.Model.Entitie.Character.CharacterRepository;
import br.com.walkflix.Utils.DefaultErroMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {
    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private ActorRepository actorRepository;

    public ResponseEntity<ApiResponse> saveFilePath(int id, String filePath){
        try{
            return characterRepository.findById(id).map(character -> {
                character.setTxCharacterPicture(filePath);
                characterRepository.save(character);

                return ResponseEntity.ok(new ApiResponse(
                        "Arquivo salvo com sucesso!",
                        character,
                        HttpStatus.OK.value()
                ));
            }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(
                    "Não foi possível salvar o caminho do arquivo: Personagem não encontrado.",
                    null,
                    HttpStatus.NOT_FOUND.value()
            )));
        } catch(Exception e){
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> createCharacter(CharacterDTO characterDTO) {
        try {
            Character character = MapperUtil.convert(characterDTO, Character.class);

            return ResponseEntity.created(URI.create("/character")).body(new ApiResponse(
                    "Personagem criado com sucesso.",
                    MapperUtil.convert(characterRepository.save(character), CharacterDTO.class),
                    HttpStatus.CREATED.value()
            ));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> editCharacter(int idCharacter, CharacterDTO characterDTO) {
        try {
            Character character = MapperUtil.convert(characterDTO, Character.class);
            character.setId(idCharacter);

            return ResponseEntity.ok(
                    new ApiResponse(
                            "Personagem editado com sucesso.",
                            MapperUtil.convert(characterRepository.save(character), CharacterDTO.class),
                            HttpStatus.OK.value()
                    )
            );
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> deleteCharacter(int id) {
        try {
            return characterRepository.findById(id).
                    map(character -> {
                        if (character.getSeries().isEmpty()) {
                            characterRepository.delete(character);

                            return ResponseEntity.ok(new ApiResponse(
                                    "Personagem excluído com sucesso!",
                                    null,
                                    HttpStatus.OK.value()
                            ));
                        } else {
                            return ResponseEntity.badRequest().body(new ApiResponse(
                                    "Não foi possível excluir o personagem: Personagem já vínculado com alguma série!",
                                    character,
                                    HttpStatus.BAD_REQUEST.value()
                            ));
                        }
                    }).orElseGet(() ->
                            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                                    new ApiResponse(
                                            "Não foi possível excluir o personagem: Personagem não encontrado",
                                            null,
                                            HttpStatus.NOT_FOUND.value()
                                    )
                            ));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }

    }

    public ResponseEntity<ApiResponse> getCharacter(int id) {
        try {
            Optional<Character> character = characterRepository.findById(id);

            return character.map(value -> ResponseEntity.ok().body(new ApiResponse(
                    "Personagem encontrado",
                    MapperUtil.convert(value, CharacterDTO.class),
                    HttpStatus.OK.value()
            ))).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(
                    "Personagem não encontrado.",
                    null,
                    HttpStatus.NOT_FOUND.value()
            )));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> getAllCharacterBySeries(int idSeries) {
        try {
            List<Character> characters = characterRepository.findAllBySeriesId(idSeries);

            return !characters.isEmpty() ? ResponseEntity.ok().body(new ApiResponse(
                    "Personagems encontrados.",
                    characters.stream().map(character -> MapperUtil.convert(characters, CharacterDTO.class)).toList(),
                    HttpStatus.OK.value()
            )) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(
                    "Nenhum personagem encontrado para esta série.",
                    null,
                    HttpStatus.NOT_FOUND.value()
            ));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }
}
