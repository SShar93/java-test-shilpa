package com.lloyds.booking.service;

import com.lloyds.booking.domain.Booking;
import com.lloyds.booking.domain.BookingRecord;
import com.lloyds.booking.helper.BookingExtractor;
import com.lloyds.booking.helper.BookingRecordExtractor;
import com.lloyds.booking.helper.BookingUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import java.util.*;


@Service
public class BookingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingService.class);
    private BookingExtractor bookingExtractor;
    private BookingRecordExtractor bookingRecordExtractor;

    @Autowired
    public BookingService(final BookingExtractor bookingExtractor, final BookingRecordExtractor bookingRecordExtractor){
        this.bookingExtractor = bookingExtractor;
        this.bookingRecordExtractor = bookingRecordExtractor;
    }

    /**
     * This method used to get the Booking Records in JSON format from String Input
     * @param input
     * @return List<BookingRecord> List of Booking records.
     */

    public List<BookingRecord> getBookingRecords(String input){
        if(Strings.isBlank(input)){
            LOGGER.info("Input cannot be null or blank");
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
        String[] bookingInformation = BookingUtils.splitInput("\\R",input,2);
        bookingExtractor.splitOfficeHours(bookingInformation[0]);
        List<String> bookingRequests = bookingExtractor.splitBookingRequests(bookingInformation[1]);
        List<Booking> bookings= bookingRequests.stream().map(x->bookingExtractor.mapBooking(x)).filter(Objects::nonNull)
                .sorted(Comparator.comparing(Booking::getMeetingSubmissionDate))
                .sorted(Comparator.comparing(Booking::getMeetingSumissionTime)).toList();
        return bookingRecordExtractor.bookingRecords(bookings);
    }
}
