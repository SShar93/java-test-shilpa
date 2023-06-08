package com.lloyds.booking.controller;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith({SpringExtension.class})
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String input = "0900 1730 \n" +
            "2020-01-18 10:17:06 EMP001 \n" +
            "2020-01-21 09:00 2 \n" +
            "2020-01-18 12:34:56 EMP002 \n" +
            "2020-01-21 09:00 2 \n" +
            "2020-01-18 09:28:23 EMP003 \n" +
            "2020-01-22 14:00 2 \n" +
            "2020-01-18 11:23:45 EMP004 \n" +
            "2020-01-22 16:00 1 \n" +
            "2020-01-15 17:29:12 EMP005 \n" +
            "2020-01-21 16:00 3 \n" +
            "2020-01-18 11:00:45 EMP006 \n" +
            "2020-01-23 16:00 1 \n" +
            "2020-01-15 11:00:45 EMP007 \n" +
            "2020-01-23 15:00 2 \n";

    @Test
    void testGetBookingRecords() throws Exception {
        mockMvc.perform(get("/meeting/booking-records")
                .content(input)).andExpect(status().isOk());
    }


    @Test
    void testGetBookingRecordsWhenInputNull() throws Exception {
        mockMvc.perform(get("/meeting/booking-records")).andExpect(status().is4xxClientError());
    }

    @Test
    void testGetBookingRecordsWhenInputBlank() throws Exception {
        mockMvc.perform(get("/meeting/booking-records")
                .content("")).andExpect(status().is4xxClientError());
    }
}
