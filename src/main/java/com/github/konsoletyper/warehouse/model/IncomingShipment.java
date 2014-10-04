package com.github.konsoletyper.warehouse.model;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Alexey Andreev <konsoletyper@gmail.com>
 */
@Entity
@Table(name = "IncomingShipments")
@SequenceGenerator(name = "IncomingShipmentGenerator", sequenceName = "IncomingShipmentGenerator",
        allocationSize = 1)
public class IncomingShipment {
    @Id
    @GeneratedValue(generator = "IncomingShipmentGenerator", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private IncomingShipmentStatus status = IncomingShipmentStatus.IN_PROGRESS;

    @Temporal(TemporalType.DATE)
    private Date shipmentDate;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "contractorId")
    private Contractor contractor;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouseId")
    private Warehouse warehouse;

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKeyJoinColumn(name = "productId")
    private Map<Product, IncomingShipmentItem> items = new HashMap<>();

    @Transient
    private transient Map<Product, IncomingShipmentItem> immutableItems;

    public IncomingShipmentStatus getStatus() {
        return status;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        checkMutable();
        this.contractor = contractor;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        checkMutable();
        this.warehouse = warehouse;
    }

    public Map<Product, IncomingShipmentItem> getItems() {
        if (immutableItems == null) {
            immutableItems = Collections.unmodifiableMap(items);
        }
        return immutableItems;
    }

    public IncomingShipmentItem addItem(Product product, int count) {
        checkMutable();
        if (items.containsKey(product)) {
            throw new IllegalStateException("Product already there: " + product.getSku());
        }
        IncomingShipmentItem item = new IncomingShipmentItem(this, product, count);
        items.put(product, item);
        return item;
    }

    public void removeItem(IncomingShipmentItem item) {
        items.remove(item.getProduct());
    }

    public void deliver() {
        checkMutable();
        checkDeliverable();
        shipmentDate = new Date();
        status = IncomingShipmentStatus.DELIVERED;
    }

    public void fail() {
        checkMutable();
        status = IncomingShipmentStatus.IN_PROGRESS;
    }


    void checkMutable() {
        if (status != IncomingShipmentStatus.IN_PROGRESS) {
            throw new IllegalStateException("Can't modify shipment as it is not in progress");
        }
    }

    void checkDeliverable() {
        if (items.isEmpty()) {
            throw new IllegalStateException("There is nothing to deliver");
        }
    }
}
