package com.github.konsoletyper.warehouse.model;

/**
 *
 * @author Alexey Andreev <konsoletyper@gmail.com>
 */
public interface ProductRepository {
    void add(Product product);

    Integer getId(Product product);

    Product findById(int id);
}
