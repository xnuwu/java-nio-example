package com.jnio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class MyClientDatagramChannel {
    public static void main(String[] args) throws IOException {
        String message = "hello, i'm datagram channel client!";
        DatagramChannel datagramChannel = DatagramChannel.open();

        ByteBuffer writeBuffer = ByteBuffer.allocate(36);
        writeBuffer.clear();
        writeBuffer.put(message.getBytes());
        writeBuffer.flip();

        datagramChannel.send(writeBuffer, new InetSocketAddress("127.0.0.1", 20));

        datagramChannel.close();
    }
}
