package com.github.konsoletyper.warehouse.infrastructure;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import com.github.konsoletyper.warehouse.model.IncomingShipment;
import com.github.konsoletyper.warehouse.model.IncomingShipmentRepository;

/**
 *
 * @author Alexey Andreev <konsoletyper@gmail.com>
 */
@Component
public class PersistentIncomingShipmentRepository implements IncomingShipmentRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(IncomingShipment shipment) {
        em.persist(shipment);
    }

    @Override
    public Integer getId(IncomingShipment shipment) {
        return (Integer)em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(shipment);
    }

    @Override
    public IncomingShipment findById(int id) {
        return em.find(IncomingShipment.class, id);
    }
}
