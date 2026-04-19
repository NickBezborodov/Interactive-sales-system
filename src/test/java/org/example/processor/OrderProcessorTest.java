package org.example.processor;

import org.example.model.Order;
import org.example.model.OrderResult;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderProcessorTest {

    @Test
    void discountCalculator() {
        LocalDateTime time1 = LocalDateTime.of(2024, 1, 1, 8, 0);
        LocalDateTime time2 = LocalDateTime.of(2024, 1, 1, 9, 0);
        LocalDateTime time3 = LocalDateTime.of(2024, 1, 1, 10, 0);

        // Заказы
        List<OrderResult> results = getOrderResults(time1, time2, time3);

        assertEquals(5000.0, results.get(0).getFinalPrice());
        assertEquals(2750.0, results.get(1).getFinalPrice());
        assertEquals(1200.0, results.get(2).getFinalPrice());
    }

    private static List<OrderResult> getOrderResults(LocalDateTime time1, LocalDateTime time2, LocalDateTime time3) {
        Order order1 = new Order(time1, "First", 1000);
        Order order2 = new Order(time2, "Second", 500);
        Order order3 = new Order(time3, "Third", 200);

        // Список заказов
        List<Order> orders = List.of(order1, order2, order3);

        // Параметры
        double priceKg = 10.0;
        double startDicsount = 50.0;
        double discountStep = 5.0;

        OrderProcessor processor = new OrderProcessor();
        List<OrderResult> results = processor.calculateCosts(orders, 10.0, 50.0, 5.0);
        return results;
    }

}
