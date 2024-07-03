package com.hotelreservation;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetAllBookingIdsTests extends BaseTest {

    @Test
    public void testGetAllBooking() {
        given(spec).
                when()
                .get("/booking")
                .then()
                .log().all()
                .statusCode(200);
    }

}
