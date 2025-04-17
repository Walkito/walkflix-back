package br.com.walkflix.Service.Actor;

import br.com.walkflix.Config.MapperUtil;
import br.com.walkflix.Model.ApiResponse;
import br.com.walkflix.Model.DTO.Actor.ActorDTO;
import br.com.walkflix.Model.Entitie.Actor.Actor;
import br.com.walkflix.Model.Entitie.Actor.ActorRepository;
import br.com.walkflix.Model.Entitie.Actor.ActorSpecification;
import br.com.walkflix.Model.Entitie.Series.Series;
import br.com.walkflix.Model.Entitie.Series.SeriesRepository;
import br.com.walkflix.Utils.DefaultErroMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ActorService {
    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private SeriesRepository seriesRepository;

    public ResponseEntity<ApiResponse> saveFilePath(int id, String filePath) {
        try {
            return actorRepository.findById(id).map(actor -> {
                actor.setTxProfilePicture(filePath);
                actorRepository.save(actor);

                return ResponseEntity.ok(new ApiResponse(
                        "Arquivo salvo com sucesso!",
                        actor,
                        HttpStatus.OK.value()
                ));
            }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(
                    "Não foi possível salvar o caminho do arquivo: Ator/Atriz não encontrado.",
                    null,
                    HttpStatus.NOT_FOUND.value()
            )));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> createActor(ActorDTO actorDTO) {
        try {
            Actor actor = MapperUtil.convert(actorDTO, Actor.class);

            return ResponseEntity.created(URI.create("/actors")).body(new ApiResponse(
                    "Ator registrado com sucesso!",
                    MapperUtil.convert(actorRepository.save(actor), ActorDTO.class),
                    HttpStatus.CREATED.value()
            ));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> editActor(int idActor, ActorDTO actorDTO) {
        try {
            Actor actor = MapperUtil.convert(actorDTO, Actor.class);
            actor.setId(idActor);

            return ResponseEntity.ok(new ApiResponse(
                    "Ator editado com sucesso!",
                    MapperUtil.convert(actorRepository.save(actor), ActorDTO.class),
                    HttpStatus.OK.value()
            ));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> deleteActor(int id) {
        try {
            return actorRepository.findById(id)
                    .map(actor -> {
                        if (!actor.getSeries().isEmpty()) {
                            return ResponseEntity.badRequest().body(new ApiResponse(
                                    "Não foi possível excluir o(a) Ator/Atriz selecionado(a): Ator/Atriz está vinculado(a) com alguma série.",
                                    null,
                                    HttpStatus.BAD_REQUEST.value()
                            ));
                        }
                        actorRepository.deleteById(id);
                        return ResponseEntity.ok(new ApiResponse(
                                "Ator/Atriz excluído(a) com sucesso.",
                                null,
                                HttpStatus.OK.value()
                        ));
                    })
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(
                            "Ator/Atriz não encontrado!",
                            null,
                            HttpStatus.NOT_FOUND.value()
                    )));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> getActor(int id) {
        try {
            Optional<Actor> actor = actorRepository.findById(id);

            return actor.map(value -> ResponseEntity.ok().body(new ApiResponse(
                    "Ator retornado com sucesso!",
                    MapperUtil.convert(value, ActorDTO.class),
                    HttpStatus.OK.value()
            ))).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse(
                            "Ator não encontrado.",
                            null,
                            HttpStatus.NOT_FOUND.value()
                    )));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> getAllActors() {
        try {
            List<Actor> actors = actorRepository.findAll();

            return !actors.isEmpty() ? ResponseEntity.ok(new ApiResponse(
                    "Atores e/ou Atrizes encontrados com sucesso.",
                    actors.stream().map(actor -> MapperUtil.convert(actor, ActorDTO.class)).toList(),
                    HttpStatus.OK.value()
            )) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(
                    "Não foi possível encontrar nenhum ator/atriz.",
                    null,
                    HttpStatus.NOT_FOUND.value()
            ));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> getAllDirectors() {
        try {
            List<Actor> directors = seriesRepository.findAllDirectorsFromSeries();

            return !directors.isEmpty() ? ResponseEntity.ok(new ApiResponse(
                    "Diretores encontrados com sucesso.",
                    directors.stream().map(director -> MapperUtil.convert(director, ActorDTO.class)).toList(),
                    HttpStatus.OK.value()
            )) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(
                    "Não foi possível encontrar nenhum diretor/diretora",
                    null,
                    HttpStatus.NOT_FOUND.value()
            ));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> findActorsWithFilter(int id, String txActorName, List<Integer> seriesId) {
        try {
            Specification<Actor> spec = ActorSpecification.filterActor(id, txActorName, seriesId);
            List<Actor> actors = actorRepository.findAll(spec);

            if (!actors.isEmpty()) {
                List<ActorDTO> actorDTOS = actors.stream().map(actor -> MapperUtil.convert(actor, ActorDTO.class)).toList();

                return ResponseEntity.ok(new ApiResponse(
                        "Atores e Atrizes encontrados com sucesso.",
                        actorDTOS,
                        HttpStatus.OK.value()
                ));
            } else {
                return ResponseEntity.ok(new ApiResponse(
                        "Não foram ecnontrados atores e atrizes",
                        null,
                        HttpStatus.OK.value()
                ));
            }

        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }

    public ResponseEntity<ApiResponse> getDirectorBySerie(int seriesId) {
        try {
            Optional<Actor> director = seriesRepository.findDirectorById(seriesId);

            return director.isPresent() ? ResponseEntity.ok(new ApiResponse(
                    "Diretor(a) encontrado(a) com sucesso.",
                    director.map(d -> MapperUtil.convert(d, ActorDTO.class)),
                    HttpStatus.OK.value()
            )) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(
                    "Não foi possível encontrar nenhum diretor(a)",
                    null,
                    HttpStatus.NOT_FOUND.value()
            ));
        } catch (Exception e) {
            return DefaultErroMessage.getDefaultError(e);
        }
    }
}
