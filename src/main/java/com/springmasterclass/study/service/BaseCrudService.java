package com.springmasterclass.study.service;

import com.springmasterclass.study.exception.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public abstract class BaseCrudService<E, ID, REQ, RES> {

    protected abstract JpaRepository<E, ID> getRepository();
    protected abstract E toEntity(REQ request);
    protected abstract void updateFromRequest(REQ request, E entity);
    protected abstract RES toResponse(E entity);
    protected abstract String getEntityName();

    protected void enrich(REQ request, E entity) {
    }

    public RES getById(ID id) {
        return toResponse(getEntityOrThrow(id));
    }

    @Transactional
    public RES create(REQ request) {
        E entity = toEntity(request);
        enrich(request, entity);
        return toResponse(getRepository().save(entity));
    }

    @Transactional
    public RES updateById(ID id, REQ request) {
        E entity = getEntityOrThrow(id);
        updateFromRequest(request, entity);
        enrich(request, entity);
        return toResponse(getRepository().save(entity));
    }

    @Transactional
    public void deleteById(ID id) {
        if (!getRepository().existsById(id)) {
            throw new NotFoundException(getEntityName() + " not found with id: " + id);
        }
        getRepository().deleteById(id);
    }

    @Transactional
    public void deleteAll() {
        getRepository().deleteAll();
    }

    // ===== Helper riêng của khung =====
    protected E getEntityOrThrow(ID id) {
        return getRepository().findById(id)
                .orElseThrow(() -> new NotFoundException(
                        getEntityName() + " not found with id: " + id));
    }
}