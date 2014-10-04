package com.github.konsoletyper.warehouse.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Alexey Andreev <konsoletyper@gmail.com>
 */
@Entity(name = "Products")
@SequenceGenerator(name = "ProductGenerator", sequenceName = "ProductGenerator", allocationSize = 1)
public class Product {
    @Id
    @GeneratedValue(generator = "ProductGenerator", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull
    @Size(min = 1, max = 15)
    @Basic
    private String sku;

    @NotNull
    @Size(min = 1, max = 200)
    @Basic
    private String name;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
