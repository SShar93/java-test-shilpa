package com.lloyds.booking.helper;


import com.lloyds.booking.TestHelper;
import com.lloyds.booking.domain.Booking;
import com.lloyds.booking.domain.BookingRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BookingRecordExtractorTest {

    @Autowired
    private BookingRecordExtractor bookingRecordExtractor;

    @Test
    @DisplayName("BookingRecordExtractorTest: Test to split the Booking request and get the booking in List of string")
    void testBookingRecords(){
        List<Booking> bookings = new ArrayList<>();
          Booking bookingFirst = Booking.builder().employeeId("EMP001")
                  .endTime("11:00")
                  .startTime("09:00")
                  .meetingSubmissionDate("2020-01-18")
                  .meetingSumissionTime("10:17:06").meetingDate("2020-01-21").build();
          Booking bookingSecond = Booking.builder().employeeId("EMP002")
                  .endTime("11:00")
                  .startTime("09:00")
                  .meetingSubmissionDate("2020-01-18")
                  .meetingSumissionTime("12:34:56").meetingDate("2020-01-21").build();
          bookings.add(bookingFirst);
          bookings.add(bookingSecond);
         List<BookingRecord> bookingRecords = bookingRecordExtractor.bookingRecords(bookings);
        Assertions.assertNotNull(bookingRecords);
    }
}
