package org.example.reader;

import org.example.model.Order;

import java.util.List;

public interface OrderReader {

    List<Order> readOrders(String filePath);

}
