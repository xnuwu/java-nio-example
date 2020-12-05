package com.jnio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class MyDatagramChannel {
    public static void main(String[] args) throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.socket().bind(new InetSocketAddress(20));

        ByteBuffer receiveBuffer = ByteBuffer.allocate(36);
        receiveBuffer.clear();

        datagramChannel.receive(receiveBuffer);

        receiveBuffer.flip();
        System.out.print("received: ");
        while (receiveBuffer.hasRemaining()) {
            System.out.print((char) receiveBuffer.get());
        }

        datagramChannel.close();
    }
}
