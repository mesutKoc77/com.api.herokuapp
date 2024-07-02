package com.hotelreservation;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetAllBookingIdsTests {

    @Test
    public void testGetAllBooking () {
        given().
                when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                .log().all()
                .statusCode(200);
    }

}
