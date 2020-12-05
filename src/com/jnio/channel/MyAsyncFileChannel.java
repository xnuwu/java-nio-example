package com.jnio.channel;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MyAsyncFileChannel {
    public static void main(String[] args) throws IOException, InterruptedException {
        AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(Paths.get("resources/data.txt"), StandardOpenOption.READ);

        ByteBuffer dataBuffer = ByteBuffer.allocate(100);
        asynchronousFileChannel.read(dataBuffer, 0, dataBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("result:" + result);

                attachment.flip();
                byte[] data = new byte[attachment.limit()];
                attachment.get(data);
                System.out.println(new String(data));
                attachment.clear();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.err.println("async file channel read failed: " + exc.getMessage());
            }
        });

        Thread.sleep(1000l);
    }
}
