package org.example.reader;

import org.example.model.Order;
import java.util.List;

public class FileOrderReaderAdapter implements OrderReader {

    @Override
    public List<Order> readOrders(String filePath) {
        OrderReader reader;
        if (filePath.endsWith(".txt")) {
            reader = new TxtOrderReader();
        } else {
            reader = new HashOrderReader();
        }
        return reader.readOrders(filePath);
    }
}