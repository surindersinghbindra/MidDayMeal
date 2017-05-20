package com.mdm.view.activities;

/**
 * Created by surinder on 20-May-17.
 */


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liangfeizc on 6/2/15.
 */
public class MyStringUtils {
    private static Pattern sWordPattern = Pattern.compile("\\w+");

    public static String capitalizeSentence(final String sentence) {
        String result = sentence;
        Matcher matcher = sWordPattern.matcher(result);
        while (matcher.find()) {
            String word = matcher.group();
            result = result.replace(matcher.group(), capitalize(word));
        }
        return result;
    }

    public static String capitalize(final String word) {
        if (word.length() > 1) {
            return String.valueOf(word.charAt(0)).toUpperCase() + word.substring(1);
        }
        return word;
    }

    public static String decinalFormatUptoTwo(double d) {

        return String.format("%.2f", d);
    }


    public static String dateFormatConvert(final String time) {

        SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat toFormat = new SimpleDateFormat("dd MMM");


        Date date = null;
        String str = null;

        try {
            date = fromFormat.parse(time);
            str = toFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;

    }
}
