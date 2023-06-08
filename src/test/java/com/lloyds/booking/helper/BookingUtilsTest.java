package com.lloyds.booking.helper;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
public class BookingUtilsTest {

    @Test
    @DisplayName("BookingUtilsTest: Test to Convert String to LocalTime")
    void testConvertStringToLocalTime(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HHmm");
        LocalTime localTime = BookingUtils.convertStringToLocalTime("0900", dateTimeFormatter);
        Assertions.assertEquals(localTime.toString(), "09:00");
    }

    @Test
    @DisplayName("BookingUtilsTest: Test to split the string with regular expression and limit")
    void testSplitInput(){
        String[] inputArray = BookingUtils.splitInput(" ", "0900 1700", 0);
        Assertions.assertEquals(inputArray[0], "0900");
    }
}
