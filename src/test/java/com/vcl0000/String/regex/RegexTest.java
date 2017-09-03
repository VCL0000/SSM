package com.vcl0000.String.regex;

import com.vcl0000.commons.LogBase;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vcl0000 on 17-8-31.
 */
public class RegexTest extends LogBase {


    @Test
    public void Test() {

        Pattern pattern = Pattern.compile(",");

        String[] strings = pattern.split("zzzzz,zzzzz,zzzz");
        for (String string : strings) {
            logger.info(string);
        }


        pattern = Pattern.compile("(xy).*y");
        String str = "xyzzy";
        Matcher matcher = pattern.matcher(str);

        matcher.reset();
        while (matcher.find()) {
            logger.info(matcher.group());
        }

        matcher.reset();
        while (matcher.find()) {
            CharSequence charSequence = str.subSequence(matcher.start(), matcher.end());
            logger.info(String.valueOf(charSequence));
        }


    }


    @Test
    public void grepInFile() throws IOException {
        Pattern pattern = Pattern.compile("[a-z]+");
        Matcher matcher = pattern.matcher("zxc123asa");


        String file = "readFile";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            matcher.reset(line);
            if (matcher.find()) {
                logger.info(file + "：" + line);
            }

        }


    }


    @Test
    public void regexReplace() {
        Pattern pattern = Pattern.compile("\\r|\\n|\\t| ");
        String str = "aaa\taaa 111\n\r";
        logger.info(str);
        Matcher matcher = pattern.matcher(str);
        String s = matcher.replaceAll("");

        logger.info(s);

    }


    @Test
    public void tail() {
        Pattern pattern = Pattern.compile("[a-z]");
        String str = "f23ref343f2";
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "-");
        }
        matcher.appendTail(sb);
        logger.info(sb.toString());
        //较replace效率高

    }

    @Test
    public void stringRegex() {
        String str = "asdasd123dsd";
        String s = str.replaceAll("[a-z]+", "-");
        logger.info(str);//asdasd123dsd
        logger.info(s);//-123-
        //String的regex无法经过编译提升效率
    }


    @Test
    public void stringFormat() {
        Date date = new Date();
        System.out.printf("date：%tT%n", date);

        String format = String.format("%tT%n", date);
        logger.info("format date:" + format);
    }


}
