package com.lloyds.booking.service;


import com.lloyds.booking.domain.BookingRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BookingServiceTest {

    @Autowired
    private BookingService bookingService;

    private static final String input = "0900 1730 \n" +
            "2020-01-18 10:17:06 EMP001 \n" +
            "2020-01-21 09:00 2 \n" +
            "2020-01-18 12:34:56 EMP002 \n" +
            "2020-01-21 09:00 2 \n" +
            "2020-01-18 09:28:23 EMP003 \n" +
            "2020-01-22 14:00 2 \n" +
            "2020-01-18 11:23:45 EMP004 \n" +
            "2020-01-22 16:00 1 \n" +
            "2020-01-15 17:29:12 EMP005 \n" +
            "2020-01-21 16:00 3 \n" +
            "2020-01-18 11:00:45 EMP006 \n" +
            "2020-01-23 16:00 1 \n" +
            "2020-01-15 11:00:45 EMP007 \n" +
            "2020-01-23 15:00 2 \n";

    private static  final String output = "[BookingRecord{data='2020-01-21', bookings=[Booking{emp_id='EMP001', start_time='09:00', end_time='11:00', meetingDate='2020-01-21'}]}, BookingRecord{data='2020-01-22', bookings=[Booking{emp_id='EMP003', start_time='14:00', end_time='16:00', meetingDate='2020-01-22'}, Booking{emp_id='EMP004', start_time='16:00', end_time='17:00', meetingDate='2020-01-22'}]}, BookingRecord{data='2020-01-23', bookings=[Booking{emp_id='EMP007', start_time='15:00', end_time='17:00', meetingDate='2020-01-23'}]}]";

    @Test
    @DisplayName("Get Records of Booking")
    public void testGetBookingRecords(){
        List<BookingRecord> bookingRecordList =  bookingService.getBookingRecords(input);
        Assertions.assertEquals(bookingRecordList.toString(), output);
    }

    @Test
    @DisplayName("test when input is Blank")
    public void testGetBookingRecordsWithException(){
       HttpClientErrorException httpClientErrorException = Assertions.assertThrows(HttpClientErrorException.class, () -> {
       bookingService.getBookingRecords(" ");
       });
       Assertions.assertNotNull(httpClientErrorException.getMessage());
    }

}
