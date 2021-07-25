package ru.job4j.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized String getContent() throws IOException {
        return getContentByPredicate((data) -> true);
    }

    private String getContentByPredicate(Predicate<Integer> condition) throws IOException {
        StringBuilder str = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int data;
            while ((data = br.read()) > 0) {
                if (condition.test(data)) {
                    str.append((char) data);
                }
            }
        }
        return str.toString();
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        return getContentByPredicate((data) -> data < 0x80);
    }
}
