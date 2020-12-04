package com.jnio.gather;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MyGatherWrite {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("resources/out.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        ByteBuffer header = ByteBuffer.allocate(10);
        ByteBuffer body = ByteBuffer.allocate(200);

        header.put((byte) '1');
        header.put((byte) '2');
        header.put((byte) '3');
        header.put((byte) '4');
        header.put((byte) '5');
        header.put((byte) '6');
        header.put((byte) '7');
        header.put((byte) '8');
        header.put((byte) '9');

        for(int i = 0; i < 200; i++) {
            body.put((byte) 'x');
        }

        ByteBuffer[] byteBuffers = { header, body };

        header.flip();
        body.flip();

        long writeSize = fileChannel.write(byteBuffers);
        System.out.println("write size " + writeSize);
    }
}
