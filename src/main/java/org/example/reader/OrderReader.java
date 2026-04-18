package org.example.reader;

import org.example.model.Order;

import java.time.format.DateTimeFormatter;
import java.util.List;


public interface OrderReader {

    int TIMESTAMP_INDEX = 0;
    int COMPANY_INDEX = 1;
    int QUANTITY_INDEX = 2;

    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    List<Order> readOrders(String filePath);

}
