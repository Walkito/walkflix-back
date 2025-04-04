package br.com.walkflix.Model.Entitie.Series;

import br.com.walkflix.Model.Entitie.Actor.Actor;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SeriesSpecification {

    public static Specification<Series> filterSeries(int id, String txSeriesName, List<Integer> directors){
        return (Root<Series> root,CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (id != 0){
                predicates.add(builder.equal(root.get("id"), id));
            }

            if(txSeriesName != null && !txSeriesName.isEmpty()){
                predicates.add(builder.like(builder.lower(root.get("txSeriesName")), txSeriesName.toLowerCase() + "%"));
            }

            if(!directors.isEmpty() && directors.getFirst() != 0){
                predicates.add(root.get("director").get("id").in(directors));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
