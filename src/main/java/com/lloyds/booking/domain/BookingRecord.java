package com.lloyds.booking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.LinkedHashSet;

@Builder
@Data
public class BookingRecord {

    @JsonProperty("data")
    private String date;
    @JsonProperty("bookings")
    private LinkedHashSet<Booking> bookings;

    @Override
    public String toString() {
        return "BookingRecord{" +
                "data='" + date + '\'' +
                ", bookings=" + bookings +
                '}';
    }
}
