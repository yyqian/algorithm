package com.yyqian.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MultiPortEcho {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        ServerSocket ss = ssc.socket();
        InetSocketAddress address = new InetSocketAddress(8888);
        ss.bind(address);

        SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int num = selector.select(); //  blocks until at least one of the registered events occurs
            System.out.println("# of events: " + num);
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectedKeys.iterator();
            while (it.hasNext()) {
                key = it.next();
                if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                    ssc = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    SelectionKey newKey = sc.register(selector, SelectionKey.OP_READ);
                    System.out.println("Accepted new connection");
                } else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    System.out.println("Client Request");
                }
                it.remove();
            }
        }
    }
}
