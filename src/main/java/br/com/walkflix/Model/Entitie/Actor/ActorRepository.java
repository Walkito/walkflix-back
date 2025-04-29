package br.com.walkflix.Model.Entitie.Actor;

import br.com.walkflix.Model.Entitie.Series.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Integer>, JpaSpecificationExecutor<Actor> {
    @Query("SELECT DISTINCT s FROM Actor a JOIN a.series s WHERE a.id = :id ORDER BY s.id")
    List<Series> findSeriesByActorId(@Param("id") int id);
}
