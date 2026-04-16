package org.example;

import org.example.manager.OrderManager;
import org.example.processor.OrderProcessor;
import org.example.reader.FileOrderReaderAdapter;
import org.example.reader.OrderReader;
import org.example.writer.OrderFileWriter;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Укажите входной и выходной файлы");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];


        FileOrderReaderAdapter adapter = new FileOrderReaderAdapter();
        OrderProcessor processor = new OrderProcessor();
        OrderFileWriter writer = new OrderFileWriter();
        OrderManager manager = new OrderManager(adapter, writer, processor);
        manager.processOrders(inputFile, outputFile);
        System.out.println("Обработка завершена. Результат сохранён в " + outputFile);

    }
}