package com.hotelreservation;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetBookingIdTests extends _BaseTestForCreate {

    @Test
    public void testGetBookingId() {

        int bookingid = createBookingResponseBody("24-10-2024","26-11-2024","Ahmet","Baykar",1500, false, "No needs ...").get("bookingid");
        System.out.println("bookinId " +bookingid);


        Response response = given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/"+bookingid);

        response.then().statusCode(200);
        String firstname = response.jsonPath().getString("firstname");
        Assertions.assertEquals("Baykar", response.jsonPath().getString("lastname"));
        Assertions.assertEquals("Ahmet", firstname);


    }

}
