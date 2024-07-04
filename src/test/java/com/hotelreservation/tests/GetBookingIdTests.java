package com.hotelreservation.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetBookingIdTests extends BaseTest {

    @Test
    public void testGetBookingId() {

        createBooking("24-10-2024", "26-11-2024", "Ahmet", "Baykar", 1500, false, "No needs ...");

        Response response = given(spec)
                .when()
                .get("/booking/" + bookingId);

        response.then().statusCode(200);
        String firstname = response.jsonPath().getString("firstname");
        Assertions.assertEquals("Baykar", response.jsonPath().getString("lastname"));
        Assertions.assertEquals("Ahmet", firstname);


    }

}
