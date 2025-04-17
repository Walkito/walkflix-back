package br.com.walkflix.Model.Entitie.Actor;

import br.com.walkflix.Model.Entitie.Series.Series;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ActorSpecification {
    public static Specification<Actor> filterActor(int id, String txActorName, List<Integer> series){
        return (Root<Actor> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (id != 0){
                predicates.add(builder.equal(root.get("id"), id));
            }

            if(txActorName != null && !txActorName.isEmpty()){
                predicates.add(builder.like(builder.lower(root.get("txActorName")), txActorName.toLowerCase() + "%"));
            }

            if(!series.isEmpty() && series.getFirst() != 0){
                Join<Actor, Series> joinSeries = root.join("series");
                predicates.add(joinSeries.get("id").in(series));
                assert query != null;
                query.distinct(true);
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
