package br.com.walkflix.Model.Entitie.Character;

import br.com.walkflix.Model.Entitie.Actor.Actor;
import br.com.walkflix.Model.Entitie.Series.Series;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CharacterSpecification {
    public static Specification<Character> filterCharacter(int id, String characterName, List<Integer> series,
                                                           List<Integer> actors){
        return (Root<Character> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (id != 0){
                predicates.add(builder.equal(root.get("id"), id));
            }

            if (!characterName.isEmpty()){
                predicates.add(builder.equal(root.get("txCharacterName"), characterName));
            }

            if(!series.isEmpty() && series.getFirst() != 0){
                Join<Character, Series> joinSeries = root.join("series");
                predicates.add(joinSeries.get("id").in(series));
                assert query != null;
                query.distinct(true);
            }

            if(!actors.isEmpty() && actors.getFirst() != 0){
                predicates.add(root.get("actor").get("id").in(actors));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
