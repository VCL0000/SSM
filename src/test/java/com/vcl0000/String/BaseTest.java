package com.vcl0000.String;

import com.vcl0000.commons.TestLogBase;
import org.junit.Test;

/**
 * Created by vcl0000 on 17-8-24.
 */
public class BaseTest extends TestLogBase {

    @Test
    public void unicdoeTest() {
        char c = '\u0041';
        char c1 = 0x7ECF;
        char c2 = '\u262F';
        logger.info(String.valueOf(c));
        logger.info(String.valueOf(c1));
        logger.info(String.valueOf(c2));

    }
}
