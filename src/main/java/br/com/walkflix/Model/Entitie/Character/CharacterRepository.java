package br.com.walkflix.Model.Entitie.Character;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, Integer> {

    List<Character> findAllBySeriesId(int id);
}
