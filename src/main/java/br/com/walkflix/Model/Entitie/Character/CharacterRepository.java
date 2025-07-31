package br.com.walkflix.Model.Entitie.Character;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, Integer>, JpaSpecificationExecutor<Character> {

    List<Character> findAllBySeriesId(int id);
}
