package com.jnio.selector;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MySelector {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = null;
        new MySelector().checkChannel(socketChannel);
    }

    public void checkChannel(SocketChannel channel) throws IOException {
        Selector selector = Selector.open();

        //only no blocking io an register selector, FileChannel is not blocking io
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);

        while (true) {

            //return immediately whatever
            int readyCount = selector.selectNow();
            if(readyCount == 0) {
                continue;
            }

            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeySet.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                if(key.isAcceptable()) {
                    System.out.println("SelectionKey Acceptable");
                }

                if(key.isConnectable()) {
                    System.out.println("SelectionKey Connectable");
                }

                if(key.isReadable()) {
                    System.out.println("SelectionKey Readable");
                }

                if(key.isWritable()) {
                    System.out.println("SelectionKey Writable");
                }

                //have to manual remove selectionKey from selectionKeySet
                iterator.remove();
            }
        }
    }
}
