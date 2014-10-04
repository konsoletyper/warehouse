package com.github.konsoletyper.warehouse.infrastructure;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import com.github.konsoletyper.warehouse.model.Contractor;
import com.github.konsoletyper.warehouse.model.ContractorRepository;

/**
 *
 * @author Alexey Andreev <konsoletyper@gmail.com>
 */
@Component
public class PersistentContractorRepository implements ContractorRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(Contractor contractor) {
        em.persist(contractor);
    }

    @Override
    public Integer getId(Contractor contractor) {
        return (Integer)em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(contractor);
    }

    @Override
    public Contractor findById(int id) {
        return em.find(Contractor.class, id);
    }
}
