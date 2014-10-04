package com.github.konsoletyper.warehouse.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Alexey Andreev <konsoletyper@gmail.com>
 */
@Entity
@Table(name = "Contractors")
@SequenceGenerator(name = "ContractorGenerator", sequenceName = "ContractorGenerator",
        allocationSize = 1)
public class Contractor {
    // Used purely by JPA
    @Id
    @GeneratedValue(generator = "ContractorGenerator", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull
    @Size(min = 1, max = 100)
    @Basic
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
