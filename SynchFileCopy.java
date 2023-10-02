package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
public class SynchFileCopy{
    public static void main(String[] args) {
        Path source = Path.of("src/Synchfile1");
        Path destination = Path.of("src/src_file1");
        try {
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Файл скопирован с использованием синхронного метода");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
