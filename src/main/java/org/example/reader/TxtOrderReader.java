package org.example.reader;

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
            return Files.lines(Paths.get(filePath)) // 1. Использовать Files.lines(Paths.get(filePath)) → Stream<String>
                    .filter(line -> !line.trim().isEmpty())// 2. Отфильтровать пустые строки: filter(line -> !line.trim().isEmpty())
                    .map(line -> { // 3. Для каждой строки
                        String[] parts = line.split("\\|"); //    - split("\\|") → массив из 3 частей
                        LocalDateTime timestamp = LocalDateTime.parse(parts[0].trim(), FORMATTER); //    - parts[0] → распарсить в LocalDateTime через FORMATTER
                        String company = parts[1].trim(); //    - parts[1] → название компании, убрать пробелы .trim()
                        int quantity = Integer.parseInt(parts[2].trim()); //    - parts[2] → количество, Integer.parseInt(.trim())

                        return new Order(timestamp, company, quantity); //    - создать new Order(время, компания, количество)
                    })
                    .collect(Collectors.toList()); // 4. Собрать всё в List через .collect(Collectors.toList())
        } catch (IOException e) { // 5. Вернуть список
            throw new RuntimeException("Ошибка чтения файла: " + filePath, e); // 6. Обработать IOException (можно обернуть в try-catch и бросить RuntimeException)
        }
    }
}

