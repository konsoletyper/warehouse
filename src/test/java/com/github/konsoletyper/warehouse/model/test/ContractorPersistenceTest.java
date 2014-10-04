package com.github.konsoletyper.warehouse.model.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.github.konsoletyper.warehouse.model.Contractor;

/**
 *
 * @author Alexey Andreev <konsoletyper@gmail.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/spring/context.xml")
@Transactional
public class ContractorPersistenceTest {
    @PersistenceContext
    private EntityManager em;

    @Test
    public void save() {
        Contractor contractor = new Contractor();
        contractor.setName("Roga i co");
        em.persist(contractor);
        em.flush();
        em.clear();

        List<Contractor> contractors = em.createQuery("select contr from Contractor contr", Contractor.class)
                .getResultList();

        assertThat(contractors.size(), is(1));
        assertThat(contractors.get(0).getName(), is("Roga i co"));
    }
}
