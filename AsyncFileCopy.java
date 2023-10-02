package com.company;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;
public class AsyncFileCopy {
    public static void main(String[] args) {
        Path source = Path.of("src/Asyncfile2");
        Path destination = Path.of("src/src_file2");
        try (AsynchronousFileChannel sourceChannel = AsynchronousFileChannel.open(source, StandardOpenOption.READ);
             AsynchronousFileChannel destinationChannel = AsynchronousFileChannel.open(destination, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            Future<Integer> operation = sourceChannel.read(buffer, 0);
            operation.get();
            buffer.flip();
            Future<Integer> writeOperation = destinationChannel.write(buffer, 0);
            writeOperation.get();
            System.out.println("Файл скопирован с использованием асинхронного метода");
        } catch (IOException | InterruptedException | java.util.concurrent.ExecutionException e) {
            e.printStackTrace();
        }
    }
}