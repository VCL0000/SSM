package com.vcl0000.nio;

import com.vcl0000.commons.LogBase;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Set;

/**
 * Created by vcl0000 on 17-8-30.
 */
public class SelectTest extends LogBase {


    @Test
    public void selectTest() throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        ServerSocketChannel ssc = ServerSocketChannel.open();
        Selector selector = Selector.open();

        ssc.bind(new InetSocketAddress(2333));
        ssc.configureBlocking(false);
        ssc.register(selector, SelectionKey.OP_ACCEPT);


        while (true) {
            int select = selector.select(10000);
            logger.info(String.valueOf(select));

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            for (SelectionKey selectionKey : selectionKeys) {


                if (selectionKey.isAcceptable()) {

                    ServerSocketChannel socketServer = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel acceptChannel = socketServer.accept();

                    acceptChannel.configureBlocking(false);
                    acceptChannel.register(selector, SelectionKey.OP_READ);


                    if (acceptChannel != null && acceptChannel.isConnected()) {
//                    if (selectionKey.isReadable()) {
                        int count = 0;
                        buffer.clear();

                        logger.info("readAble");
                        if ((count = acceptChannel.read(buffer)) > 0) {
                            buffer.flip();
                            while (buffer.hasRemaining()) {
                                byte[] bytes = new byte[count];
                                buffer.get(bytes);
                                logger.info(new String(bytes));

                            }
                        }
                    }

                    if (!selectionKeys.isEmpty()) {
                        selectionKeys.remove(selectionKey);
                    }
                }

            }


        }


    }
}
