package org.example.manager;

import org.example.model.Order;
import org.example.model.OrderResult;
import org.example.processor.OrderProcessor;
import org.example.reader.FileOrderReaderAdapter;
import org.example.reader.OrderReader;
import org.example.writer.OrderFileWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderManagerTest {

    @Mock
    private FileOrderReaderAdapter adapter;
    @Mock
    private OrderFileWriter writer;
    @Mock
    private OrderProcessor processor;
    @Mock
    private OrderReader reader;
    @InjectMocks
    private OrderManager manager;

    @Test
    void processOrders_ShouldCallDependenciesInCorrectOrder() {
        String inputFile = "input.txt";
        String outputFile = "output.txt";
        double pricePerKg = 10.0;
        double startDiscount = 50.0;
        double discountStep = 5.0;

        List<Order> mockOrders = List.of();
        List<OrderResult> mockResult = List.of();


        when(adapter.getReader(inputFile)).thenReturn(reader);
        when(reader.readOrders(inputFile)).thenReturn(mockOrders);
        when(processor.calculateCosts(mockOrders, pricePerKg, startDiscount, discountStep))
                .thenReturn(mockResult);

        manager.processOrders(inputFile, outputFile, pricePerKg, startDiscount, discountStep);

        verify(adapter).getReader(inputFile);
        verify(reader).readOrders(inputFile);
        verify(processor).calculateCosts(mockOrders, pricePerKg, startDiscount, discountStep);
        verify(writer).writeResult(mockResult, outputFile);
    }
}


