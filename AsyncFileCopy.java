package com.company;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class AsyncFileCopy {
    public static void main(String[] args) {
        Path source = Path.of("src/Asyncfile2");
        Path destination = Path.of("src/src_file2");

        try (AsynchronousFileChannel sourceChannel = AsynchronousFileChannel.open(source, StandardOpenOption.READ);
             AsynchronousFileChannel destinationChannel = AsynchronousFileChannel.open(destination, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            // Чтение из источника
            sourceChannel.read(buffer, 0, null, new CompletionHandler<Integer, Void>() {
                @Override
                public void completed(Integer result, Void attachment) {
                    buffer.flip();

                    // Запись в целевой файл
                    destinationChannel.write(buffer, 0, null, new CompletionHandler<Integer, Void>() {
                        @Override
                        public void completed(Integer result, Void attachment) {
                            System.out.println("Файл скопирован с использованием асинхронного метода");
                        }

                        @Override
                        public void failed(Throwable exc, Void attachment) {
                            exc.printStackTrace();
                        }
                    });
                }

                @Override
                public void failed(Throwable exc, Void attachment) {
                    exc.printStackTrace();
                }
            });

            // Ждем завершения операций
            Thread.sleep(1000); // Это не лучший способ, но для простоты

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}