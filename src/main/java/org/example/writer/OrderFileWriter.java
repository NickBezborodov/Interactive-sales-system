package org.example.writer;

import org.example.model.OrderResult;
import org.example.exception.IORuntimeException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class OrderFileWriter {
    public void writeResult(List<OrderResult> results, String filePath) {
        try {
            List<String> lines = results.stream()
                    .map(result -> result.getCompanyName() + " - " + result.getFinalPrice())
                    .toList();

            Files.write(Paths.get(filePath), lines);
        } catch (IOException e) {
            throw new IORuntimeException("Ошибка записи в файл: " + filePath, e);
        }
    }
}
