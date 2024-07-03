package com.hotelreservation;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    public void testGetSpecificBooking() {
        createBooking("15", "20", "saygin", "adam", 320, false, "Query");

        spec.queryParam("firstname", "saygin");
        spec.queryParam("lastname", "adam");

        Response response = given(spec)
                .when()
                .get("/booking");

        response.then().statusCode(200);

        List<Integer> bookingids = response.jsonPath().getList("bookingid");
        Assertions.assertTrue(bookingids.contains(bookingId));

    }

}
