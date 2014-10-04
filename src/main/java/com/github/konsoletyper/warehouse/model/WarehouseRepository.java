package com.github.konsoletyper.warehouse.model;

/**
 *
 * @author Alexey Andreev <konsoletyper@gmail.com>
 */
public interface WarehouseRepository {
    void add(Warehouse warehouse);

    Integer getId(Warehouse warehouse);

    Warehouse findById(int id);
}
