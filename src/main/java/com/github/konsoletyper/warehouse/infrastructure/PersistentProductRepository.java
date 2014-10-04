package com.github.konsoletyper.warehouse.infrastructure;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import com.github.konsoletyper.warehouse.model.Product;
import com.github.konsoletyper.warehouse.model.ProductRepository;

/**
 *
 * @author Alexey Andreev <konsoletyper@gmail.com>
 */
@Component
public class PersistentProductRepository implements ProductRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(Product product) {
        em.persist(product);
    }

    @Override
    public Integer getId(Product product) {
        return (Integer)em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(product);
    }

    @Override
    public Product findById(int id) {
        return em.find(Product.class, id);
    }
}
