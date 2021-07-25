package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveToFile {
    private final File file;

    public SaveToFile(File file) {
        this.file = file;
    }

    public synchronized void saveContent(String content) throws IOException {
        Files.writeString(Paths.get(file.toURI()), content);
    }
}
