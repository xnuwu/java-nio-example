package com.jnio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class MyServerSocketChannel {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(20));
        serverSocketChannel.configureBlocking(false);
        System.out.println("server listen at " + 20);

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if(socketChannel != null) {

                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

                int readBytes = socketChannel.read(readBuffer);
                while (readBytes != -1) {

                    System.out.print("SERVER Rev " + readBytes + " Bytes: ");
                    readBuffer.flip();
                    while (readBuffer.hasRemaining()) {
                        System.out.print((char) readBuffer.get());
                    }
                    System.out.println();

                    String message = "hello, client! server time is " + System.currentTimeMillis();
                    writeBuffer.put(message.getBytes());
                    writeBuffer.flip();
                    socketChannel.write(writeBuffer);

                    readBuffer.clear();
                    writeBuffer.clear();
                    readBytes = socketChannel.read(readBuffer);
                }
            }
        }
    }
}
