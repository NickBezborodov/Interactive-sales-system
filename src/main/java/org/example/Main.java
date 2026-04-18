package org.example;

import org.example.manager.OrderManager;
import org.example.processor.OrderProcessor;
import org.example.reader.FileOrderReaderAdapter;
import org.example.writer.OrderFileWriter;

public class Main {
    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("Укажите входной и выходной файлы");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];
        double pricePerKg = Double.parseDouble(args[2]);
        double startDiscount = Double.parseDouble(args[3]);
        double discountStep = Double.parseDouble(args[4]);


        FileOrderReaderAdapter adapter = new FileOrderReaderAdapter();
        OrderFileWriter writer = new OrderFileWriter();
        OrderManager manager = new OrderManager(adapter, writer);
        manager.processOrders(inputFile, outputFile, pricePerKg, startDiscount, discountStep);
        System.out.println("Обработка завершена. Результат сохранён в " + outputFile);

    }
}