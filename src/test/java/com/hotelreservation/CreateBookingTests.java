package com.hotelreservation;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateBookingTests extends BaseTest {

    @Test
    public void testCreateBooking() {

        Response bookingResponseBody = createBooking("2024-10-12", "2024-10-23", "Ali", "KOC", 500, true, "No Need");

        JsonPath jsonPath = bookingResponseBody.jsonPath();

        Assertions.assertEquals("Ali", jsonPath.get("booking.firstname"));
        Assertions.assertEquals("2024-10-12", jsonPath.get("booking.bookingdates.checkin"));
        Assertions.assertEquals(500, (Integer) jsonPath.get("booking.totalprice"));
    }
}
