package com.hotelreservation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteBookingTests extends BaseTest {


    @Test
    public void testDeleteBooking() {

        createBooking("x", "y", "Aydin", "ayaydin", 850, false, "Breakfast");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + getAuthToken())
                .delete("/booking/" + bookingId);

        response.then().statusCode(201);


    }

}
