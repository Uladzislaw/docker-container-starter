package com.uladzislau.docker_container_starter.util;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@AllArgsConstructor
public class BufferedInfoFileHandlingHelper {

    private final String path;

    @SneakyThrows
    public File createFile() {
        File file = new File(path);
        file.createNewFile();
        return file;
    }

    @SneakyThrows
    public boolean contains(String key) {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
        return bufferedReader.lines().anyMatch(line -> line.contains(key));
    }

    public void deleteFile() {
        new File(path).delete();
    }
}
