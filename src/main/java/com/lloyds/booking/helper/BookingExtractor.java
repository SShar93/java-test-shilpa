package com.lloyds.booking.helper;

import com.lloyds.booking.domain.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.lloyds.booking.helper.BookingUtils.convertStringToLocalTime;

@Component
public class BookingExtractor {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingExtractor.class);

    private LocalTime officeStartTime;
    private LocalTime officeEndTime;

    /**
     * Get and split the office hours from the string input.
     * @param input
     */
    public void splitOfficeHours(String input) {
        String[] officeHours = BookingUtils.splitInput(" ", input ,0);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HHmm");
        this.officeStartTime = convertStringToLocalTime(officeHours[0],dateTimeFormatter);
        this.officeEndTime = convertStringToLocalTime(officeHours[1],dateTimeFormatter);
        LOGGER.info("Office start hours : {}, Office end hours : {}", this.officeStartTime, this.officeEndTime);
    }

    /**
     * split the booking request from string input
     * @param bookingRequest
     * @return
     */
    public List<String> splitBookingRequests(String bookingRequest){
        String regex = "\\b\\d{4}-\\d{2}-\\d{2} \\d{1,2}:\\d{2}:\\d{2}\\b \\b\\w+\\b \\s+\\b\\d{4}-\\d{2}-\\d{2}\\b \\b\\d{1,2}:\\d{2}\\b \\b\\d{1}\\b";
        Matcher matcher = Pattern.compile(regex).matcher(bookingRequest);
        List<String> bookings = matcher.results().map(MatchResult::group).collect(Collectors.toList());
        LOGGER.info("Booking request Array: {} ",bookings);
        return bookings;
    }

    /**
     * build the booking object for getting the booking records.
     * @param booking
     * @return Booking
     */

    public Booking mapBooking(String booking){
        String[] bookingDetails = this.splitBooking(booking);
        return checkOfficeHoursAndBuildBooking(bookingDetails[0],
                bookingDetails[1],
                bookingDetails[2],
                bookingDetails[3],
                bookingDetails[4],
                bookingDetails[5]);

    }

    /*
  This is Private method to split the booking request.
  */
    private String[] splitBooking(String booking){
        return BookingUtils.splitInput(" ", booking,0);
    }


 /*
  This is Private method to check whether booking is in office hours and build the booking object.
  */
    private Booking checkOfficeHoursAndBuildBooking(String bookingSubmissionDate, String bookingSubmissionTime,
                                                           String employeeId, String bookingDate, String bookingStartTime, String bookingDuration ) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startTime =  convertStringToLocalTime(bookingStartTime, dateTimeFormatter);
        LocalTime endTime = startTime.plusHours(Long.parseLong(bookingDuration));
        if(startTime.isBefore(this.officeStartTime) || endTime.isAfter(this.officeEndTime)){
            LOGGER.info("Can't process the booking request as it is not in office hours for emp : {}", employeeId);
            return null;
        }
        return Booking.builder()
                .startTime(String.valueOf(startTime))
                .endTime(String.valueOf(endTime))
                .employeeId(employeeId)
                .meetingSubmissionDate(bookingSubmissionDate)
                .meetingSumissionTime(bookingStartTime)
                .meetingDate(bookingDate.trim()).build();
    }
}
