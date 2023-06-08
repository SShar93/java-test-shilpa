package com.lloyds.booking.service;

import com.lloyds.booking.domain.Booking;
import com.lloyds.booking.domain.BookingRecord;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingService.class);
    private LocalTime officeStartTime;
    private LocalTime officeEndTime;

    /**
     * This method used to get the Booking Records from Text Input
     * @param input
     * @return List<BookingRecord> List of Booking records.
     */

    public List<BookingRecord> getBookingRecords(String input){
        if(Strings.isBlank(input)){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
        String bookingRequest = splitOfficeHours(input);
        return splitBookingRequest(bookingRequest);
    }

    /**
     * This Method Used to separate the office hours and Booking information.
     * @param input
     * @return booking information in string format.
     */
    private String splitOfficeHours(String input) {
        String[] inputArray = input.split("\\R",2);
        String[] officeHours = inputArray[0].split(" ");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HHmm");
        this.officeStartTime = convertStringToLocalTime(officeHours[0],dateTimeFormatter);
        this.officeEndTime = convertStringToLocalTime(officeHours[1],dateTimeFormatter);
        LOGGER.info("Office start hours : {}, Office end hours : {}", this.officeStartTime, this.officeEndTime);
        return inputArray[1];
    }

    /**
     * spilt the Booking information, map the booking details and return list of booking record.
     * @param bookingRequest
     * @return list of Booking records/
     */

    private List<BookingRecord> splitBookingRequest(String bookingRequest){
        LOGGER.info("Booking request string : {} ",bookingRequest );
        String regex = "\\b\\d{4}-\\d{2}-\\d{2} \\d{1,2}:\\d{2}:\\d{2}\\b \\b\\w+\\b \\s+\\b\\d{4}-\\d{2}-\\d{2}\\b \\b\\d{1,2}:\\d{2}\\b \\b\\d{1}\\b";
        Matcher matcher = Pattern.compile(regex).matcher(bookingRequest);
        List<String> bookings = matcher.results().map(MatchResult::group).collect(Collectors.toList());
        LOGGER.info("Booking request Array: {} ",bookings);
        List<Booking> bookingList = bookings.stream().map(this::splitAndMapBookingRequest).filter(x->x!=null)
                .sorted(Comparator.comparing(Booking::getMeetingSubmissionDate))
                .sorted(Comparator.comparing(Booking::getMeetingSumissionTime)).collect(Collectors.toList());
        this.officeEndTime = null ; this.officeStartTime = null;
        return mapBookingRecord(bookingList).entrySet().stream()
                .map(x-> BookingRecord.builder()
                        .bookings(x.getValue()).date(x.getKey()).build())
                .sorted(Comparator.comparing(BookingRecord::getDate))
                .collect(Collectors.toList());
    }

    /**
     * map the booking details and return object of Booking
     */

    private Booking splitAndMapBookingRequest(String booking){
        String[] meetingDetails = booking.split(" ");
        String meetingSubmissionDate = meetingDetails[0];
        String meetingSubmissionTime = meetingDetails[1];
        String employeeId = meetingDetails[2];
        String meetingDate = meetingDetails[3];
        String meetingStartTime = meetingDetails[4];
        String meetingDuration = meetingDetails[5];
        return checkOfficeHoursAndBuildBookingRequest(meetingStartTime,meetingDuration, employeeId,meetingSubmissionDate, meetingSubmissionTime, meetingDate);

    }

    /**
     * Build Booking object
     * @param meetingStartTime
     * @param meetingDuration
     * @param employeeId
     * @param meetingSubmissionDate
     * @param meetingSubmissionTime
     * @param meetingDate
     * @return booking object
     */
    private Booking checkOfficeHoursAndBuildBookingRequest(String meetingStartTime, String meetingDuration, String employeeId, String meetingSubmissionDate , String meetingSubmissionTime, String meetingDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startTime =  convertStringToLocalTime(meetingStartTime,dateTimeFormatter);
        LocalTime endTime = startTime.plusHours(Long.parseLong(meetingDuration));
        if(startTime.isBefore(this.officeStartTime) || endTime.isAfter(this.officeEndTime)){
            LOGGER.info("Can't process the booking request as it is not in office hours for emp : {}", employeeId);
            return null;
        }
        return Booking.builder()
                .startTime(String.valueOf(startTime))
                .endTime(String.valueOf(endTime))
                .employeeId(employeeId)
                .meetingSubmissionDate(meetingSubmissionDate)
                .meetingSumissionTime(meetingStartTime)
                .meetingDate(meetingDate.trim()).build();
    }

    /**
     * create Linkedhashmap for Bookings request
     * @param bookingList
     * @return
     */
    private LinkedHashMap<String,LinkedHashSet<Booking>> mapBookingRecord(List<Booking> bookingList) {
        LinkedHashMap<String, LinkedHashSet<Booking>> bookingRecords = new LinkedHashMap<>();
        for(Booking booking : bookingList){
            if(bookingRecords.containsKey(booking.getMeetingDate())){
                LinkedHashSet<Booking> value= bookingRecords.get(booking.getMeetingDate());
                value.add(booking);
                value.stream().map(x->LocalTime.parse(x.getStartTime())).sorted(Comparator.comparing(c->c));
                bookingRecords.put(booking.getMeetingDate(), value);
            }else{
                LinkedHashSet<Booking> bookingSet = new LinkedHashSet<>();
                bookingSet.add(booking);
                bookingRecords.put(booking.getMeetingDate(), bookingSet);
            }
        }
        return bookingRecords;
    }

    /**
     * convert String to time.
     * @param time
     * @param dateTimeFormatter
     * @return
     */

    private LocalTime convertStringToLocalTime(String time, DateTimeFormatter dateTimeFormatter){
        return  LocalTime.parse(time, dateTimeFormatter);
    }
}
