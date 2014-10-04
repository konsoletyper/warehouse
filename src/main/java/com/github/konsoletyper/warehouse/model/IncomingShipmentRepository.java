package com.github.konsoletyper.warehouse.model;

/**
 *
 * @author Alexey Andreev <konsoletyper@gmail.com>
 */
public interface IncomingShipmentRepository {
    void add(IncomingShipment shipment);

    Integer getId(IncomingShipment shipment);

    IncomingShipment findById(int id);
}
