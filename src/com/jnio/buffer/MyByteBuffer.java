package com.jnio.buffer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: yangxunwu
 * @date: 2020/12/3 8:24
 */
public class MyByteBuffer {

    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("resources/data.txt", "rw");
        FileChannel fileChannel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(64);

        int readBytes = fileChannel.read(buffer);
        System.out.println("read bytes " + readBytes);
        showBufferStat(buffer);

        buffer.flip();
        System.out.println("read a char: " + ((char) buffer.get()));
        showBufferStat(buffer);

        System.out.println("rewind ByteBuffer");
        buffer.rewind();
        showBufferStat(buffer);

        System.out.println("mark ByteBuffer, and get a char");
        buffer.mark();
        buffer.get();
        showBufferStat(buffer);

        System.out.println("reset ByteBuffer");
        buffer.reset();
        showBufferStat(buffer);
    }

    public static void showBufferStat(ByteBuffer buffer) {
        System.out.println("remaining " + buffer.remaining() + ", position " + buffer.position() + ", limit " + buffer.limit() + ", capacity " + buffer.capacity());
    }
}
