package com.vcl0000.String;

import com.vcl0000.commons.TestLogBase;
import com.vcl0000.constant.Constant;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by vcl0000 on 17-9-2.
 */
public class EncoderTest extends TestLogBase {


    @Test
    public void encoderTest() {

        String source = "\u00bfMa\u00f1ana?";

        logger.info(source);
        String[] charsetNames = {
                "US-ASCII", "ISO-8859-1", "UTF-8", "UTF-16BE",
                "UTF-16LE", "UTF-16",
        };

        for (String charsetName : charsetNames) {
            Charset charset = Charset.forName(charsetName);
            ByteBuffer target = charset.encode(source);
            logger.info(charsetName);
            /*byte[] bytes = new byte[target.remaining()];
            target.get(bytes);
            logger.info(new String(bytes));*/
            char[] chars = new char[target.remaining()];
            for (int i = 0; target.hasRemaining(); i++) {
                byte b = target.get();
                char c = (char) (b & 0xFF);

                if (!Character.isSpaceChar(c) && !Character.isISOControl(c)) {
                    chars[i] = c;
                }

            }
            logger.info(new String(chars));

        }
    }


    @Test
    public void test() {
        String zzz = new String("中文");
        logger.info(zzz);
        logger.info(Charset.defaultCharset().name());
        String gbk = new String(zzz.getBytes(), Charset.forName("UTF-8"));
        logger.info(gbk);
    }


}
