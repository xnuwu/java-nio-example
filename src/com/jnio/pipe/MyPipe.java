package com.jnio.pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class MyPipe {
    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();

        String message = "this is message send from sink client!";

        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
        Pipe.SinkChannel sinkChannel = pipe.sink();

        writeBuffer.put(message.getBytes());

        writeBuffer.flip();
        while (writeBuffer.hasRemaining()) {
            sinkChannel.write(writeBuffer);
        }

        System.out.println("pipe sink channel send over!");

        Pipe.SourceChannel source = pipe.source();
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        source.read(readBuffer);

        readBuffer.flip();
        System.out.print("pipe source get message: ");
        while (readBuffer.hasRemaining()) {
            System.out.print((char) readBuffer.get());
        }
    }
}
