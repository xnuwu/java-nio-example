package com.jnio.channel;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class MySocketChannel {

    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 20));

        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
        writeBuffer.put(("hello, i'm socket channel".getBytes()));
        writeBuffer.flip();
        socketChannel.write(writeBuffer);

        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int readBytes = socketChannel.read(readBuffer);
        while (readBytes != -1) {

            System.out.print(readBytes + "-server: ");
            readBuffer.flip();
            while (readBuffer.hasRemaining()) {
                System.out.print((char) readBuffer.get());
            }
            System.out.println();
            readBuffer.clear();
            readBytes = socketChannel.read(readBuffer);
        }
    }

}
