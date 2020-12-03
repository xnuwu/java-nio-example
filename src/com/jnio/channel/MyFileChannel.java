package com.jnio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: yangxunwu
 * @date: 2020/12/3 7:59
 */
public class MyFileChannel {

    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("resources/data.txt", "rw");
        FileChannel fileChannel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(64);
        int readBytes = fileChannel.read(buffer);
        while (readBytes != -1) {
            System.out.println("\r\n------------------------ Read " + readBytes + " ------------------------");

            buffer.flip();

            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }

            buffer.clear();
            readBytes = fileChannel.read(buffer);
        }

        fileChannel.close();
    }

}
