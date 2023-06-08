package com.lloyds.booking.helper;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class BookingUtils {

    /**
     * convert String to time.
     * @param time
     * @param dateTimeFormatter
     * @return LocalTime
     */

    public static LocalTime convertStringToLocalTime(String time, DateTimeFormatter dateTimeFormatter){
        return  LocalTime.parse(time, dateTimeFormatter);
    }

    /**
     * split the string with regular expression and limit.
     * @param regex
     * @param input
     * @param limit
     * @return String[]
     */
    public static String[] splitInput(String regex, String input, int limit){
        return limit >= 0 ? input.split(regex,limit) : input.split(regex);
    }
}
