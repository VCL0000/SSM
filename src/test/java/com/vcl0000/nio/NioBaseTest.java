package com.vcl0000.nio;

import com.vcl0000.constant.Constant;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by vcl0000 on 17-8-23.
 */
public class NioBaseTest {
    private static Logger logger = LoggerFactory.getLogger(NioBaseTest.class);



    @Test
    public void readFile() throws IOException {
        // plan 1
        /*FileInputStream fin = new FileInputStream("readFile");
        FileChannel fc = fin.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = fc.read(buffer);
        Buffer flip = buffer.flip();
        int limit = flip.limit();
        logger.info(new java.lang.String(buffer.array(), 0, limit, UTF_8));*/


        //plan 2
        RandomAccessFile aFile = new RandomAccessFile("readFile", "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int bytesRead = inChannel.read(buf); //read into buffer.

        while (bytesRead != -1) {

            Buffer flip = buf.flip();//make buffer ready for read
            int limit = flip.limit();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get()); // read 1 byte at a time
            }
            System.out.print("\r\n");

            buf.clear(); //make buffer ready for writing
            bytesRead = inChannel.read(buf);

            logger.info(new String(buf.array(), 0, limit, Constant.UTF_8));
        }
        aFile.close();
    }


    @Test
    public void writeFile() throws IOException {
        FileOutputStream fout = new FileOutputStream("writeFile");
        FileChannel fc = fout.getChannel();
        byte[] message = "zzzz".getBytes();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        for (int i = 0; i < message.length; ++i) {
            buffer.put(message[i]);
        }
        buffer.flip();
        fc.write(buffer);
    }
}

