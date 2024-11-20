package org.fintech.store.specification;

import jakarta.persistence.criteria.Predicate;
import org.fintech.api.model.FilterCondition;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BaseSpecification<T> {

    public Specification<T> buildSpecification(List<FilterCondition> filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            List<Predicate> shouldPredicates = new ArrayList<>();
            for (FilterCondition filter : filters) {
                String field = filter.getField();
                if (filter.getValue() != null) {
                    if (filter.getIsLike() != null && filter.getIsLike()) {
                        predicates.add(criteriaBuilder.like(root.get(field), "%" + filter.getValue() + "%"));
                    } else {
                        predicates.add(criteriaBuilder.equal(root.get(field), filter.getValue()));
                    }
                }

                if (filter.getMinValue() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(field), (Comparable) filter.getMinValue()));
                }

                if (filter.getMaxValue() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(field), (Comparable) filter.getMaxValue()));
                }

                if (filter.getMustBe() != null && !filter.getMustBe().isEmpty()) {
                    predicates.add(root.get(field).in(filter.getMustBe()));
                }
                if (filter.getMustNot() != null && !filter.getMustNot().isEmpty()) {
                    predicates.add(criteriaBuilder.not(root.get(field).in(filter.getMustNot())));
                }

                if (filter.getShould() != null && !filter.getShould().isEmpty()) {
                    shouldPredicates.add(root.get(field).in(filter.getShould()));
                }
            }
            Predicate mustPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            Predicate shouldPredicate = shouldPredicates.isEmpty()
                    ? criteriaBuilder.conjunction()
                    : criteriaBuilder.or(shouldPredicates.toArray(new Predicate[0]));
            return criteriaBuilder.and(mustPredicate, shouldPredicate);
        };
    }
}
