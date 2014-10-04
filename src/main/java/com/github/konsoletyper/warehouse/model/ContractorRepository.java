package com.github.konsoletyper.warehouse.model;

/**
 *
 * @author Alexey Andreev <konsoletyper@gmail.com>
 */
public interface ContractorRepository {
    void add(Contractor contractor);

    Integer getId(Contractor contractor);

    Contractor findById(int id);
}
