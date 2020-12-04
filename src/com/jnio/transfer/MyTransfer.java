package com.jnio.transfer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class MyTransfer {

    public static void main(String[] args) throws IOException {

        RandomAccessFile from = new RandomAccessFile("resources/from.txt", "rw");
        FileChannel fromChannel = from.getChannel();

        RandomAccessFile to = new RandomAccessFile("resources/to.txt", "rw");
        FileChannel toChannel = to.getChannel();

        long position = 0;
        long count = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);

        fromChannel.transferTo(position, count, toChannel);

    }
}
