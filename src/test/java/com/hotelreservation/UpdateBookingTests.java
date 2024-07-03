package com.hotelreservation;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UpdateBookingTests extends BaseTest {

    @Test
    public void testUpdate() {

        createBooking("2024-10-16", "2024-10-25", "Ahmet", "Nadir", 500, true, "no need");

        JSONObject updatedBody = createBookingBody("2024-10-18", "2024-10-20", "Mehmet", "Test", 600, false, "NeeD");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + getAuthToken())
                .body(updatedBody.toString())
                .put("/booking/" + bookingId);

        System.out.println(bookingId);

        response.then().statusCode(200);

        JsonPath jsonResponse = response.jsonPath();
        Assertions.assertEquals("Mehmet", jsonResponse.get("firstname"));

        assertEquals("Test", jsonResponse.getString("lastname"));
        assertEquals(600, jsonResponse.getInt("totalprice"));
        assertFalse(jsonResponse.getBoolean("depositpaid"));
        assertEquals("2024-10-18", jsonResponse.getString("bookingdates.checkin"));
        assertEquals("2024-10-20", jsonResponse.getString("bookingdates.checkout"));
        assertEquals("NeeD", jsonResponse.getString("additionalneeds"));

    }


}
