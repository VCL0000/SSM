package com.vcl0000.nio;

import com.vcl0000.commons.TestLogBase;
import com.vcl0000.commons.util.Unsigned;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

/**
 * Created by vcl0000 on 17-8-26.
 */
public class ByteBufferTest extends TestLogBase {


    @Test
    public void direct_ByteOrder() {
        ByteOrder byteOrder = ByteOrder.nativeOrder();
        logger.info(byteOrder.toString());

        ByteBuffer directBuffer = ByteBuffer.allocateDirect(1024);
        logger.info(String.valueOf(directBuffer.isDirect()));

    }

    @Test
    public void bufferCharView() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(7).order(ByteOrder.BIG_ENDIAN);
        CharBuffer charBuffer = byteBuffer.asCharBuffer();
        byteBuffer.put(0, (byte) 0);
        byteBuffer.put(1, (byte) 'H');
        byteBuffer.put(2, (byte) 0);
        byteBuffer.put(3, (byte) 'i');
        byteBuffer.put(4, (byte) 0);
        byteBuffer.put(5, (byte) '!');
        byteBuffer.put(6, (byte) 0);
        logger.info(byteBuffer.toString());
        logger.info(charBuffer.toString());

    }

    @Test
    public void unsigned() {

        ByteBuffer buffer = ByteBuffer.allocate(20);

        buffer.clear();
        Unsigned.putUnsignedByte(buffer, 255);
        Unsigned.putUnsignedByte(buffer, 128);
        Unsigned.putUnsignedShort(buffer, 0xcafe);
        Unsigned.putUnsignedInt(buffer, 0xcafebabe);

        for (int i = 0; i < 8; i++) {
            logger.info("" + i + ": "
                    + Integer.toHexString((int) Unsigned.getUnsignedByte(buffer, i)));
        }

        logger.info("2: " + Integer.toHexString(Unsigned.getUnsignedShort(buffer, 2)));
        logger.info("4: " + Long.toHexString(Unsigned.getUnsignedInt(buffer, 4)));
    }
}
