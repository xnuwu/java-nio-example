package com.jnio.scatter;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MyScatterRead {

    public static void main(String[] args) throws IOException {

        RandomAccessFile randomAccessFile = new RandomAccessFile("resources/data.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        ByteBuffer header = ByteBuffer.allocate(10);
        ByteBuffer body = ByteBuffer.allocate(1023);

        ByteBuffer[] byteBuffers = { header, body };

        fileChannel.read(byteBuffers);

        System.out.println("------------ header ------------");
        header.flip();
        while (header.hasRemaining()) {
            System.out.print((char) header.get());
        }

        System.out.println("------------ body ------------");
        body.flip();
        while (body.hasRemaining()) {
            System.out.print((char) body.get());
        }
    }
}
