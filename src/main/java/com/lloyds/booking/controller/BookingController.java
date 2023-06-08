package com.lloyds.booking.controller;

import com.lloyds.booking.domain.BookingRecord;
import com.lloyds.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meeting")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    /**
     * Get API to see booking record for meetings.
     * @param input
     * @return list of Booking record
     */
    @GetMapping(value = "/booking-records")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<BookingRecord>> getBookingRecords(@RequestBody @Validated String input){
        List<BookingRecord> bookingRecords = bookingService.getBookingRecords(input);
     return new ResponseEntity<>(bookingRecords, HttpStatus.OK);
    }
}
