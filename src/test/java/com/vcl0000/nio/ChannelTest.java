package com.vcl0000.nio;

import com.vcl0000.commons.LogBase;
import com.vcl0000.constant.Constant;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by vcl0000 on 17-8-26.
 */
public class ChannelTest extends LogBase {


    @Test
    public void readOnly() throws IOException {
        FileInputStream fis = new FileInputStream("readFile");
        FileChannel fisChannel = fis.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("zzz".getBytes());
        fisChannel.write(byteBuffer);//while Exception because it's read only

    }

    @Test
    public void testCopy() throws IOException {
        ReadableByteChannel source = Channels.newChannel(System.in);
        WritableByteChannel dest = Channels.newChannel(System.out);
        channelCopy(source, dest);
        source.close();
        dest.close();


    }

    public void channelCopy(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(16 * 1024);
        while (src.read(byteBuffer) != -1) {
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                dest.write(byteBuffer);
            }
            byteBuffer.clear();
        }

    }


    @Test
    public void scatterGather() throws IOException {
        ByteBuffer header = ByteBuffer.allocateDirect(10);
        ByteBuffer body = ByteBuffer.allocateDirect(60);
        ByteBuffer[] byteBuffers = {header, body};


        RandomAccessFile accessFile = new RandomAccessFile("", "rw");
        FileChannel channel = accessFile.getChannel();
        long read = channel.read(byteBuffers);
        logger.info(String.valueOf(read));


    }

    @Test
    public void mapper() throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile("readWrite", "rw");
        FileChannel fileChannel = accessFile.getChannel();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size());
        byte[] buf = new byte[1024];
        int limit = mappedByteBuffer.limit();
        mappedByteBuffer.get(buf, 0, limit);
        logger.info(new String(buf, 0, buf.length));
        mappedByteBuffer.put(0, (byte) '0');

        fileChannel.write(mappedByteBuffer);

    }

    @Test
    public void tempFile() throws IOException, InterruptedException {
        File tempFile = File.createTempFile("file", ".file");
        RandomAccessFile file = new RandomAccessFile(tempFile, "rw");

        logger.info(tempFile.getPath());
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put((byte) 'z');
        byteBuffer.flip();
        FileChannel channel = file.getChannel();
        channel.write(byteBuffer);

        Thread.sleep(6000000);
        tempFile.delete();


    }


    @Test
    public void channelTransfer() throws IOException {
        RandomAccessFile in = new RandomAccessFile("readFile", "rw");
        RandomAccessFile out = new RandomAccessFile("writeFile", "rw");

        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();


//        inChannel.transferTo(0, inChannel.size(), outChannel);//outSize<inSize
        outChannel.transferFrom(inChannel, 0, inChannel.size());


        MappedByteBuffer mappedByteBuffer = inChannel.map(FileChannel.MapMode.PRIVATE, 0, inChannel.size());
        byte[] buf = new byte[(int) inChannel.size()];
        mappedByteBuffer.get(buf);
        logger.info("read:" + new String(buf, 0, buf.length));

        byte[] buf1 = new byte[(int) outChannel.size()];
        MappedByteBuffer mappedByteBuffer1 = outChannel.map(FileChannel.MapMode.PRIVATE, 0, outChannel.size());
        mappedByteBuffer1.get(buf1);
        logger.info("write:" + new String(buf1, 0, buf1.length));

    }


    @Test
    public void pipeTest() throws IOException {

        Pipe pipe = Pipe.open();

        Pipe.SinkChannel sink = pipe.sink();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.clear();
        buffer.put("zz".getBytes());
        buffer.flip();
        while (buffer.hasRemaining()) {
            sink.write(buffer);
        }

        Pipe.SourceChannel source = pipe.source();
        buffer.clear();
        source.read(buffer);
        buffer.flip();
        byte[] buf = new byte[buffer.remaining()];
        buffer.get(buf);
        logger.info(new String(buf));


    }


    @Test
    public void conversTest() {

        ReadableByteChannel inChannel = Channels.newChannel(System.in);
        WritableByteChannel outChannel = Channels.newChannel(System.out);

        InputStream inputStream = Channels.newInputStream(inChannel);
        OutputStream outputStream = Channels.newOutputStream(outChannel);

        Reader reader = Channels.newReader(inChannel, Constant.UTF_8);
        Writer writer = Channels.newWriter(outChannel, Constant.UTF_8);

    }


}
