package org.example.processor;

import org.example.model.Order;
import org.example.model.OrderResult;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrderProcessor {

    private static final double PRICE_PER_KG = 10.0;
    private static final double START_DISCOUNT_PERCENT = 50.0;
    private static final double DISCOUNT_STEP_PERCENT = 5.0;


    public List<OrderResult> calculateCosts(List<Order> orders) {

        // 1. Сортируем заказы по времени
        List<Order> sortedOrders = orders.stream()
                .sorted(Comparator.comparing(Order::getTimestamp))
                .collect(Collectors.toList());


        List<OrderResult> results = new ArrayList<>();

        double currentDiscount = START_DISCOUNT_PERCENT;

        for (Order order : sortedOrders) {
            // Считаем стоимость без скидки
            double costWithoutDiscount = order.getQuantityKg() * PRICE_PER_KG;

            // применил скидку
            double finalCost = costWithoutDiscount * (1 - currentDiscount / 100);

            // Создаём результат
            OrderResult result = new OrderResult(order.getCompanyName(), finalCost);
            results.add(result);

            // Уменьшаем скидку для следующего заказа
            currentDiscount = currentDiscount - DISCOUNT_STEP_PERCENT;
            if (currentDiscount < 0) {
                currentDiscount = 0;
            }

        }
        return results;
    }
}