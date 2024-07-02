package com.hotelreservation;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateBookingTests extends _BaseTestForCreate {

    @Test
    public void testCreateBooking(){

        JsonPath bookingResponseBody = createBookingResponseBody("2024-10-12", "2024-10-23", "Mesut", "KOC", 500, true, "No Need");

        Assertions.assertEquals("Mesut", bookingResponseBody.get("booking.firstname"));
        Assertions.assertEquals("2024-10-12", bookingResponseBody.get("booking.bookingdates.checkin"));
        Assertions.assertEquals(500,(Integer) bookingResponseBody.get("booking.totalprice"));
    }
}
