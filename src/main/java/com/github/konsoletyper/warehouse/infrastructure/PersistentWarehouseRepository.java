package com.github.konsoletyper.warehouse.infrastructure;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import com.github.konsoletyper.warehouse.model.Warehouse;
import com.github.konsoletyper.warehouse.model.WarehouseRepository;

/**
 *
 * @author Alexey Andreev <konsoletyper@gmail.com>
 */
@Component
public class PersistentWarehouseRepository implements WarehouseRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(Warehouse warehouse) {
        em.persist(warehouse);
    }

    @Override
    public Integer getId(Warehouse warehouse) {
        return (Integer)em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(warehouse);
    }

    @Override
    public Warehouse findById(int id) {
        return em.find(Warehouse.class, id);
    }
}
