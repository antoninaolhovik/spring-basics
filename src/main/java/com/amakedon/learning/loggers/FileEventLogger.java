package com.amakedon.learning.loggers;

import com.amakedon.learning.beans.Event;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileEventLogger implements EventLogger {

    private Path path;

    public FileEventLogger(String fileName) {
        this.path = Paths.get(fileName);
    }

    @PostConstruct
    public void init() {

        System.out.println("Save to file: " + path);
        if (Files.exists(path) && !Files.isWritable(path)) {
            throw new IllegalArgumentException("Can't write to file " + path.getFileName());
        } else if(Files.notExists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new IllegalArgumentException("Can't create file", e);
            }
        }
    }

    public void logEvent(Event event) {
        try {
            Files.write(path, event.toString().getBytes(), StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
