package com.project.core.entities;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public abstract class BaseService<T extends EntityHandle, ID> {
    protected final BaseRepository<T, ID> repository;

    protected BaseService(BaseRepository<T, ID> repository) {
        this.repository = repository;
    }

    public T save(T entity) {
        setDefaultDeletedFlag(entity);
        return repository.save(entity);
    }

    public Set<T> saveAll(Set<T> entities) {
        entities.forEach(this::setDefaultDeletedFlag);
        return new HashSet<>(repository.saveAll(entities));
    }

    public T delete(ID id) {
        T entity = findById(id);
        if (entity != null) {
            entity.setIsDeleted(true);
            return repository.save(entity);
        }
        return null;
    }

    public T findById(ID id) {
        return findByFields(Map.of("id", id));
    }

    public List<T> findAll() {
        return repository.findAll(
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isDeleted"), false));
    }

    public T findByFields(Map<String, Object> fields) {
        return repository.findOne(
                        (root, query, criteriaBuilder) -> criteriaBuilder.and(buildPredicates(fields, criteriaBuilder, root).toArray(new Predicate[0])))
                .orElse(null);
    }

    public List<T> findAllByFields(Map<String, Object> fields) {
        return repository.findAll(
                (root, query, criteriaBuilder) -> criteriaBuilder.and(buildPredicates(fields, criteriaBuilder, root).toArray(new Predicate[0])));
    }

    private void setDefaultDeletedFlag(T entity) {
        if (entity.getIsDeleted() == null) {
            entity.setIsDeleted(false);
        }
    }

    private List<Predicate> buildPredicates(Map<String, Object> fields, CriteriaBuilder criteriaBuilder, Root<T> root) {
        List<Predicate> predicates = new ArrayList<>();
        fields.forEach((field, value) -> predicates.add(criteriaBuilder.equal(root.get(field), value)));
        predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
        return predicates;
    }
}
