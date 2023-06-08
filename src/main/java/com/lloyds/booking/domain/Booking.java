package com.lloyds.booking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Builder
@Data
public class Booking {
    private static final Logger LOGGER = LoggerFactory.getLogger(Booking.class);


    @JsonProperty("emp_id")
    private String employeeId;
    @JsonProperty("start_time")
    private String startTime;
    @JsonProperty("end_time")
    private String endTime;

    @JsonIgnore
    private String meetingSubmissionDate;
    @JsonIgnore
    private String meetingSumissionTime;
    @JsonIgnore
    private String meetingDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        if(LocalDate.parse(meetingSubmissionDate).isAfter(LocalDate.parse(booking.meetingSubmissionDate))){
            if((LocalTime.parse(startTime).isBefore(LocalTime.parse(booking.startTime)) || Objects.equals(startTime, booking.startTime))&&
                    (LocalTime.parse(endTime).isAfter(LocalTime.parse(booking.endTime)) ||  Objects.equals(endTime, booking.endTime)) ||
                    (LocalTime.parse(startTime).isAfter(LocalTime.parse(booking.startTime)) ||  Objects.equals(startTime, booking.startTime))  &&
                            (LocalTime.parse(endTime).isBefore(LocalTime.parse(booking.endTime)) ||  Objects.equals(endTime, booking.endTime)) ){
                LOGGER.info("TimeSlot is already booked, Please choose another slot");
                return true;
            }
            return false;
        }
        if(LocalTime.parse(startTime).isBefore(LocalTime.parse(booking.startTime)) &&
                (LocalTime.parse(endTime).isBefore(LocalTime.parse(booking.endTime)) ||  Objects.equals(endTime, booking.endTime)) ||
                (LocalTime.parse(startTime).isAfter(LocalTime.parse(booking.startTime)) ||  Objects.equals(startTime, booking.startTime))  &&
                        LocalTime.parse(endTime).isBefore(LocalTime.parse(booking.endTime))){
            LOGGER.info("TimeSlot is already booked, Please choose another slot");
           return true;
        }
        return  Objects.equals(startTime, booking.startTime) && Objects.equals(endTime, booking.endTime);
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "emp_id='" + employeeId + '\'' +
                ", start_time='" + startTime + '\'' +
                ", end_time='" + endTime + '\'' +
                ", meetingDate='" + meetingDate + '\'' +
                '}';
    }
}
