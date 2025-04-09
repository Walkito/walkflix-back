package br.com.walkflix.Service.Actor;

import br.com.walkflix.Model.Entitie.Actor.Actor;
import br.com.walkflix.Model.Entitie.Actor.ActorRepository;
import br.com.walkflix.Model.Enum.ActorStatus.ActorStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ActorServiceTest {
    @Mock
    private ActorRepository actorRepository;

    @Autowired
    @InjectMocks
    private ActorService actorService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create an Actor")
    void createActorCase1() {
        Actor newActor = new Actor();
        newActor.setTxActorName("Walker");
        newActor.setTxActorSurname("de Carvalho");
        newActor.setStatus(ActorStatus.ATIVO);

        when(actorRepository.save(newActor)).thenReturn(newActor);

        Actor savedActor = actorRepository.save(newActor);

        verify(actorRepository, times(1)).save(newActor);

        assertNotNull(savedActor);
    }

    @Test
    @DisplayName("Should throw a Exception when are creating an Actor")
    void createActorCase2() throws Exception{
        Actor newActor = new Actor();
        newActor.setTxActorName("Walker");

        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            actorRepository.save(newActor);
        });
    }
}