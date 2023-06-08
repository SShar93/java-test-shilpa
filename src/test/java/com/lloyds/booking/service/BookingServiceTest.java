package com.lloyds.booking.service;


import com.lloyds.booking.TestHelper;
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

    @Test
    @DisplayName("BookingServiceTest: Test to get all the booking records for meetings in a boardroom")
    public void testGetBookingRecords(){
        List<BookingRecord> bookingRecordList =  bookingService.getBookingRecords(TestHelper.INPUT);
        Assertions.assertEquals(bookingRecordList.toString(), TestHelper.OUTPUT);
    }

    @Test
    @DisplayName("BookingServiceTest: Test to get Exception message when we send blank input")
    public void testGetBookingRecordsWithException(){
       HttpClientErrorException httpClientErrorException = Assertions.assertThrows(HttpClientErrorException.class, () -> {
       bookingService.getBookingRecords(" ");
       });
       Assertions.assertNotNull(httpClientErrorException.getMessage());
    }

}
