package org.example.processor;

import org.example.model.Order;
import org.example.model.OrderResult;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderProcessorTest {
    private Order createOrder(int hour, String company, int quantity) {
        LocalDateTime time = LocalDateTime.of(2024, 1, 1, hour, 0);
        return new Order(time, company, quantity);
    }

    @Test
    void discountCalculator() {
        LocalDateTime time1 = LocalDateTime.of(2024, 1, 1, 8, 0);
        LocalDateTime time2 = LocalDateTime.of(2024, 1, 1, 9, 0);
        LocalDateTime time3 = LocalDateTime.of(2024, 1, 1, 10, 0);
        LocalDateTime time4 = LocalDateTime.of(2024, 1, 1, 11, 0);

        // Заказы
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

        assertEquals(5000.0, results.get(0).getFinalPrice());
        assertEquals(2750.0, results.get(1).getFinalPrice());
        assertEquals(1200.0, results.get(2).getFinalPrice());

    }

    @Test
    void shouldStopAtZero_WhenStartIs7AndStepIs3() {
        Order order1 = createOrder(8, "First", 100);
        Order order2 = createOrder(9, "Second", 100);
        Order order3 = createOrder(10, "Third", 100);
        Order order4 = createOrder(11, "Fourth", 100);

        List<Order> orders = List.of(order1, order2, order3, order4);
        OrderProcessor processor = new OrderProcessor();

        List<OrderResult> results = processor.calculateCosts(orders, 10.0, 7.0, 3.0);

        double[] expected = {929.9999999999999, 960.0, 990.0, 1000.0};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], results.get(i).getFinalPrice());

        }

    }

    @Test
    void forAnEmptyList() {
        List<Order> orders = List.of();

        OrderProcessor processor = new OrderProcessor();

        List<OrderResult> results = processor.calculateCosts(orders, 10.0, 50.0, 5.0);
        assertTrue(results.isEmpty());
    }

    @Test
    void priceToZero() {
        Order order1 = createOrder(8, "First", 100);
        Order order2 = createOrder(9, "Second", 200);
        Order order3 = createOrder(10, "Third", 300);

        List<Order> orders = List.of(order1, order2, order3);
        OrderProcessor processor = new OrderProcessor();

        List<OrderResult> results = processor.calculateCosts(orders, 0.0, 50.0, 5.0);
        assertEquals(0.0, results.get(0).getFinalPrice());
        assertEquals(0.0, results.get(1).getFinalPrice());
        assertEquals(0.0, results.get(2).getFinalPrice());

    }

    @Test
    void whenStartDiscountIsZero() {
        Order order1 = createOrder(8, "First", 100);
        Order order2 = createOrder(9, "Second", 200);

        List<Order> orders = List.of(order1, order2);
        OrderProcessor processor = new OrderProcessor();

        List<OrderResult> results = processor.calculateCosts(orders, 10.0, 0.0, 5.0);

        assertEquals(1000, results.get(0).getFinalPrice());
        assertEquals(2000, results.get(1).getFinalPrice());
    }

    @Test
    void whenPriceNegative() {
        Order order = createOrder(8, "First", 100);

        List<Order> orders = List.of(order);
        OrderProcessor processor = new OrderProcessor();

        List<OrderResult> results = processor.calculateCosts(orders, -10.0, 10.0, 5.0);
        assertEquals(-900.0, results.get(0).getFinalPrice());
    }

    @Test
    void whenStartDiscountIsNegative() {
        Order order = createOrder(8, "First", 100);

        List<Order> orders = List.of(order);

        OrderProcessor processor = new OrderProcessor();

        List<OrderResult> results = processor.calculateCosts(orders, 10.0, -10.0, 5.0);

        assertEquals(1100.0, results.get(0).getFinalPrice());
    }
}

