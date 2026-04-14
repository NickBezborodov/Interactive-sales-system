package org.example.reader;

public class FileOrderReaderAdapter {

    public OrderReader getReader(String filePath) {

        if (filePath.endsWith(".txt")) {
            return new TxtOrderReader();
        } else {
            return new HashOrderReader();
        }
    }
}