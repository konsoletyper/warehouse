package com.github.konsoletyper.warehouse.model.test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;
import com.github.konsoletyper.warehouse.model.*;

/**
 *
 * @author Alexey Andreev <konsoletyper@gmail.com>
 */
public class IncomingShipmentTest {
    private Product product = new Product();
    private Contractor contractor = new Contractor();
    private Warehouse mainWarehouse = new Warehouse();

    public IncomingShipmentTest() {
        product.setName("Computer");
        product.setSku("123");
        contractor.setName("Roga i co");
        mainWarehouse.setName("Main");
    }

    @Test
    public void delivers() {
        IncomingShipment shipment = new IncomingShipment();
        shipment.setContractor(contractor);
        shipment.setWarehouse(mainWarehouse);
        shipment.addItem(product, 1);

        shipment.deliver();

        assertThat(shipment.getStatus(), is(IncomingShipmentStatus.DELIVERED));
        assertThat(shipment.getShipmentDate(), is(not(nullValue())));
    }

    @Test(expected = IllegalStateException.class)
    public void doesNotDeliverEmptyShipment() {
        IncomingShipment shipment = new IncomingShipment();
        shipment.setContractor(contractor);
        shipment.setWarehouse(mainWarehouse);

        shipment.deliver();
    }

    @Test(expected = IllegalStateException.class)
    public void doesNotDeliverRepeatedly() {
        IncomingShipment shipment = new IncomingShipment();
        shipment.setContractor(contractor);
        shipment.setWarehouse(mainWarehouse);
        shipment.addItem(product, 1);

        shipment.deliver();
        shipment.deliver();
    }
}
