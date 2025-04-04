package br.com.walkflix.Model.Entitie.Series;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SeriesRepository extends JpaRepository<Series, Integer>, JpaSpecificationExecutor<Series> {
}
