package org.example.reader;

import org.example.model.Order;

import java.util.List;

public interface OrderReader {
    int TIMESTAMP_INDEX = 0;
    int COMPANY_INDEX = 1;
    int QUANTITY_INDEX = 2;

    List<Order> readOrders(String filePath);

}
