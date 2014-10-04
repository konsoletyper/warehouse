package com.github.konsoletyper.warehouse.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Alexey Andreev <konsoletyper@gmail.com>
 */
@Entity
@Table(name = "Warehouses")
@SequenceGenerator(name = "WarehouseGenerator", sequenceName = "WarehouseGenerator", allocationSize = 1)
public class Warehouse {
    @Id
    @GeneratedValue(generator = "WarehouseGenerator", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull
    @Size(min = 1, max = 100)
    @Basic
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
