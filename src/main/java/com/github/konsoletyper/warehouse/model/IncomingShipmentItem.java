package com.github.konsoletyper.warehouse.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Alexey Andreev <konsoletyper@gmail.com>
 */
@Entity
@Table(name = "IncomingShipmentItems")
@SequenceGenerator(name = "IncomingShipmentItemGenerator", sequenceName = "IncomingShipmentItemGenerator",
        allocationSize = 50)
public class IncomingShipmentItem {
    @Id
    @GeneratedValue(generator = "IncomingShipmentItemGenerator", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "shipmentId")
    private IncomingShipment shipment;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    @Min(1)
    @Basic
    @Column(name = "productCount")
    private int count;

    IncomingShipmentItem(IncomingShipment shipment, Product product, int count) {
        this.shipment = shipment;
        this.product = product;
        this.count = count;
    }

    public IncomingShipment getShipment() {
        return shipment;
    }

    public Product getProduct() {
        return product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        shipment.checkMutable();
        this.count = count;
    }
}
