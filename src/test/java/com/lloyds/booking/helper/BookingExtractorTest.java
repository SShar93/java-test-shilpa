package com.lloyds.booking.helper;


import com.lloyds.booking.TestHelper;
import com.lloyds.booking.domain.Booking;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
public class BookingExtractorTest {

    @Autowired
    private BookingExtractor bookingExtractor;


    @Test
    @DisplayName("BookingExtractorTest: Test to split the office Hours")
    void testSplitOfficeHours(){
        bookingExtractor.splitOfficeHours(TestHelper.OFFICE_HOURS);
       }

    @Test
    @DisplayName("BookingExtractorTest: Test to split the Booking request and get the booking in List of string")
    void testSplitBookingRequests(){
        List<String> bookings = bookingExtractor.splitBookingRequests(TestHelper.BOOKING_REQUESTS);
        Assertions.assertNotNull(bookings);
    }

    @Test
    @DisplayName("BookingExtractorTest: Test to build the booking object for getting the booking records.")
    void testMapBookings(){
        bookingExtractor.splitOfficeHours(TestHelper.OFFICE_HOURS);
       Booking booking = bookingExtractor.mapBooking(TestHelper.BOOKING);
       Assertions.assertNotNull(booking);
    }
}
