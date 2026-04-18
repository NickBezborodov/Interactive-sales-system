package org.example.processor;

import org.example.model.Order;
import org.example.model.OrderResult;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderProcessor {

    public OrderProcessor() {
    }

    public List<OrderResult> calculateCosts(List<Order> orders,
                                            double priceKg,
                                            double startDicsountPercent,
                                            double discountStepPercent) {

        // 1. Сортируем заказы по времени
        List<Order> sortedOrders = orders.stream()
                .sorted(Comparator.comparing(Order::getTimestamp))
                .toList();


        List<OrderResult> results = new ArrayList<>();

        double currentDiscount = startDicsountPercent;

        for (Order order : sortedOrders) {
            // Считаем стоимость без скидки
            double costWithoutDiscount = order.getQuantityKg() * priceKg;

            // применил скидку
            double finalCost = costWithoutDiscount * (1 - currentDiscount / 100);

            // Создаём результат
            OrderResult result = new OrderResult(order.getCompanyName(), finalCost);
            results.add(result);

            // Уменьшаем скидку для следующего заказа
            currentDiscount = currentDiscount - discountStepPercent;
            if (currentDiscount < 0) {
                currentDiscount = 0;
            }

        }
        return results;
    }
}