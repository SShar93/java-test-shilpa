package com.lloyds.booking.controller;



import com.lloyds.booking.TestHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith({SpringExtension.class})
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("BookingControllerTest : Test to get all the booking records for meetings in a boardroom")
    void testGetBookingRecords() throws Exception {
        mockMvc.perform(get(TestHelper.URL_PATH_FOR_BOOKING_RECORDS)
                .content(TestHelper.INPUT)).andExpect(status().isOk()).andExpect(content().string(TestHelper.JSON_OUTPUT));
    }


    @Test
    @DisplayName("BookingControllerTest :  Test to get Exception message when we send Null input")
    void testGetBookingRecordsWhenInputNull() throws Exception {
        mockMvc.perform(get(TestHelper.URL_PATH_FOR_BOOKING_RECORDS)).andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("BookingControllerTest :  Test to get Exception message when we send Blank input")
    void testGetBookingRecordsWhenInputBlank() throws Exception {
        mockMvc.perform(get(TestHelper.URL_PATH_FOR_BOOKING_RECORDS)
                .content("")).andExpect(status().is4xxClientError());
    }
}
