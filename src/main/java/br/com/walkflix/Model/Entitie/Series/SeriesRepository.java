package br.com.walkflix.Model.Entitie.Series;

import br.com.walkflix.Model.Entitie.Actor.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SeriesRepository extends JpaRepository<Series, Integer>, JpaSpecificationExecutor<Series> {
    @Query("SELECT DISTINCT s.director FROM Series s")
    List<Actor> findAllDirectorsFromSeries();
}
