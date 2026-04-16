package org.example.manager;

import org.example.model.Order;
import org.example.model.OrderResult;
import org.example.processor.OrderProcessor;
import org.example.reader.FileOrderReaderAdapter;
import org.example.reader.OrderReader;
import org.example.writer.OrderFileWriter;

import java.util.List;


public class OrderManager {
    private final FileOrderReaderAdapter adapter;
    private final OrderFileWriter writer;
    private final OrderProcessor processor;

    public OrderManager(FileOrderReaderAdapter adapter, OrderFileWriter writer, OrderProcessor processor) {
        this.adapter = adapter;
        this.writer = writer;
        this.processor = processor;
    }

    public void processOrders(String inputFile, String outputFile) {

        OrderReader reader = adapter.getReader(inputFile);
        List<Order> orders = reader.readOrders(inputFile);
        List<OrderResult> results = processor.calculateCosts(orders);
        writer.writeResult(results, outputFile);
    }

}
