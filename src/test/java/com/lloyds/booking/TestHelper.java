package com.lloyds.booking;

public class TestHelper {

    public static String INPUT = "0900 1730 \n" +
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


    public static String OUTPUT = "[BookingRecord{data='2020-01-21', bookings=[Booking{emp_id='EMP001', start_time='09:00', end_time='11:00', meetingDate='2020-01-21'}]}, BookingRecord{data='2020-01-22', bookings=[Booking{emp_id='EMP003', start_time='14:00', end_time='16:00', meetingDate='2020-01-22'}, Booking{emp_id='EMP004', start_time='16:00', end_time='17:00', meetingDate='2020-01-22'}]}, BookingRecord{data='2020-01-23', bookings=[Booking{emp_id='EMP007', start_time='15:00', end_time='17:00', meetingDate='2020-01-23'}]}]";

    public static String JSON_OUTPUT = "[{\"data\":\"2020-01-21\",\"bookings\":[{\"emp_id\":\"EMP001\",\"start_time\":\"09:00\",\"end_time\":\"11:00\"}]},{\"data\":\"2020-01-22\",\"bookings\":[{\"emp_id\":\"EMP003\",\"start_time\":\"14:00\",\"end_time\":\"16:00\"},{\"emp_id\":\"EMP004\",\"start_time\":\"16:00\",\"end_time\":\"17:00\"}]},{\"data\":\"2020-01-23\",\"bookings\":[{\"emp_id\":\"EMP007\",\"start_time\":\"15:00\",\"end_time\":\"17:00\"}]}]";
    public static String URL_PATH_FOR_BOOKING_RECORDS = "/meeting/booking-records";

    public static String OFFICE_HOURS = "0900 1700";
    public static String BOOKING_REQUESTS = "2020-01-18 10:17:06 EMP001 \n" +
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

    public static String BOOKING = "2020-01-18 12:34:56 EMP002 \n" +
            "2020-01-21 " +
            "09:00 2 \n";

}