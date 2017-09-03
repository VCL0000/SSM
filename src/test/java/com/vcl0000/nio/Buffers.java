package com.vcl0000.nio;

import com.vcl0000.commons.TestLogBase;
import org.junit.Test;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * Created by vcl0000 on 17-8-24.
 */
public class Buffers extends TestLogBase {

    @Test
    public void putGet() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        String zz = "zzz";
        byteBuffer.put(zz.getBytes());
        Buffer flip = byteBuffer.flip();
        int limit = flip.limit();
        byte[] result = new byte[limit];
        int count = byteBuffer.remaining();//get 一次就会减一
        for (int i = 0; i < count; i++) {
            result[i] = byteBuffer.get();
        }
        logger.info(new String(result, 0, result.length));

    }


    private static int index = 0;
    private static String[] strings = {
            "A random string value",
            "The product of an infinite number of monkeys",
            "Hey hey we're the Monkees",
            "Opening act for the Monkees: Jimi Hendrix",
            "'Scuse me while I kiss this fly", // Sorry Jimi ;-)
            "Help Me! Help Me!",
    };

    @Test
    public void bufferFillDrain() {
        CharBuffer buffer = CharBuffer.allocate(100);

        while (fillBuffer(buffer)) {
            buffer.flip();
            drainBuffer(buffer);
            buffer.clear();
        }
    }

    private static void drainBuffer(CharBuffer buffer) {
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get());
        }
        System.out.println("");
    }

    private static boolean fillBuffer(CharBuffer buffer) {
        if (index >= strings.length) {
            return (false);
        }
        String string = strings[index++];
        for (int i = 0; i < string.length(); i++) {
            buffer.put(string.charAt(i));
        }
        return (true);
    }


    @Test
    public void bufCompact() {
        ByteBuffer buffer = ByteBuffer.allocate(10);


        byte[] bytes = "1234".getBytes();
        for (int i = 0; i < bytes.length; i++) {
            buffer.put(bytes[i]);
        }
        buffer.flip();
        buffer.get();
        buffer.compact();
        buffer.put((byte) '5');
        Buffer flip = buffer.flip();
        int limit = flip.limit();
        byte[] buf = new byte[limit];

        int count = buffer.remaining();//get 一次就会减一
        for (int i = 0; i < count; i++) {
            buf[i] = buffer.get();
        }
        logger.info(new String(buf));

    }

    @Test
    public void markReset() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        byte[] bytes = "12345".getBytes();
        buffer.put(bytes);
        logger.info(buffer.toString());
        buffer.position(2).mark().position(4);
        logger.info(buffer.toString());
        buffer.reset();
        logger.info(buffer.toString());
    }

    @Test
    public void batchMove() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        byte[] bytes = "12345".getBytes();
        buffer.put(bytes);
        byte[] buf = new byte[buffer.remaining()];
        buffer.flip();
        buffer.get(buf);
        logger.info(new String(buf));

        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("zzzzz");
        charBuffer.flip();
        char[] chars = new char[charBuffer.remaining()];
        charBuffer.get(chars);
        logger.info(new String(chars));
    }


    @Test
    public void createCharBuffer() {

        CharBuffer charBuffer = CharBuffer.allocate(1024);
        char[] chars = new char[1024];
        CharBuffer buffer = CharBuffer.wrap(chars);


        for (int i = 0; i < 10; i++) {
            chars[i] = (char) i;
        }

        CharBuffer wrap = CharBuffer.wrap(chars, 3, 5);

        CharBuffer charBufferz = CharBuffer.wrap("zzz");
        logger.info(charBufferz.toString());

    }


    @Test
    public void copy() {
        //原始缓冲区和副本都会操作同样的数据元素
        CharBuffer charBuffer = CharBuffer.allocate(8);
        charBuffer.position(3).limit(6).mark().position(5);
        CharBuffer dupeBuffer = charBuffer.duplicate();

        //slice() 容量是原始缓冲区的剩余元素数量(limit-position)
        CharBuffer buffer = CharBuffer.allocate (8);
        buffer.position (3).limit (5);
        CharBuffer sliceBuffer = buffer.slice( );

    }


}
