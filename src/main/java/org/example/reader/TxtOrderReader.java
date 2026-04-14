package org.example.reader;

import org.example.exception.IORuntimeException;
import org.example.model.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class TxtOrderReader implements OrderReader {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<Order> readOrders(String filePath) {
        try {
            return Files.lines(Paths.get(filePath))
                    .filter(line -> !line.trim().isEmpty())
                    .map(line -> {
                        String[] parts = line.split("\\|");
                        LocalDateTime timestamp = LocalDateTime.parse(parts[TIMESTAMP_INDEX].trim(), FORMATTER);
                        String company = parts[COMPANY_INDEX].trim();
                        int quantity = Integer.parseInt(parts[QUANTITY_INDEX].trim());

                        return new Order(timestamp, company, quantity);
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IORuntimeException("Ошибка чтения файла: " + filePath, e);
        }
    }
}

