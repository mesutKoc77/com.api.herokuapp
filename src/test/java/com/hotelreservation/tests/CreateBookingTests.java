package com.hotelreservation.tests;

import com.hotelreservation.models.Booking;
import com.hotelreservation.models.BookingDates;
import com.hotelreservation.models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreateBookingTests extends BaseTest {

    @Test
    public void testCreateBooking() {

        Response bookingResponseBody = createBooking("2024-10-12", "2024-10-23", "Ali", "KOC", 500, true, "No Need");

        JsonPath jsonPath = bookingResponseBody.jsonPath();

        Assertions.assertEquals("Ali", jsonPath.get("booking.firstname"));
        Assertions.assertEquals("2024-10-12", jsonPath.get("booking.bookingdates.checkin"));
        Assertions.assertEquals(500, (Integer) jsonPath.get("booking.totalprice"));
    }

    @Test
    public void createBookingWithPojo(){
        BookingDates bookingDates=new BookingDates("2024-10-13","2024-10-19");
        Booking booking=new Booking("Udemy","Kurs",500, false, bookingDates,"Wifi" );

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking");
        response
                .then()
                .statusCode(200);

        BookingResponse bookingResponse = response.as(BookingResponse.class);

        Assertions.assertEquals("Udemy",bookingResponse.getBooking().getFirstname());

    }



}
