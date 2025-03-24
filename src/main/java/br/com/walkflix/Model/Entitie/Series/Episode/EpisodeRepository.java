package br.com.walkflix.Model.Entitie.Series.Episode;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
    List<Episode> findAllBySeriesId(int id);
}
