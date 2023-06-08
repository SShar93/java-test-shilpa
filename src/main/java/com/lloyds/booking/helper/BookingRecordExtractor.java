package com.lloyds.booking.helper;

import com.lloyds.booking.domain.Booking;
import com.lloyds.booking.domain.BookingRecord;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class BookingRecordExtractor {

    /**
     * Return List of Booking records after removing duplicate entries and collect the meeting on date-wise.
     * @param bookingList
     * @return
     */
    public List<BookingRecord> bookingRecords(List<Booking> bookingList){
        return this.mapBookingRecord(bookingList).entrySet().stream()
                .map(x-> BookingRecord.builder()
                        .bookings(x.getValue()).date(x.getKey()).build())
                .sorted(Comparator.comparing(BookingRecord::getDate))
                .collect(Collectors.toList());
    }

    /**
     * create Linkedhashmap for All booking to collect date-wise and make sure there will be no overlap in bookings.
     * @param bookingList
     * @return LinkedHashMap.
     */
    private LinkedHashMap<String, LinkedHashSet<Booking>> mapBookingRecord(List<Booking> bookingList) {
        LinkedHashMap<String, LinkedHashSet<Booking>> bookingRecords = new LinkedHashMap<>();
        for(Booking booking : bookingList){
            if(bookingRecords.containsKey(booking.getMeetingDate())){
                LinkedHashSet<Booking> value= bookingRecords.get(booking.getMeetingDate());
                value.add(booking);
                value.stream().map(x-> LocalTime.parse(x.getStartTime())).sorted(Comparator.comparing(c->c));
                bookingRecords.put(booking.getMeetingDate(), value);
            }else{
                LinkedHashSet<Booking> bookingSet = new LinkedHashSet<>();
                bookingSet.add(booking);
                bookingRecords.put(booking.getMeetingDate(), bookingSet);
            }
        }
        return bookingRecords;
    }
}
