package com.hotelreservation;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PartialUpdateBookingTests extends BaseTest {

    @Test
    public void partialUpdateBooking() {

        createBooking("2023-05-12", "2023-05-20", "Partial", "Update", 100, false, "JA");

        JSONObject partialJson = new JSONObject();
        partialJson.put("firstname","Sezgi");
        partialJson.put("lastname","KARA");

        Response response=given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + getAuthToken())
                .body(partialJson.toString())
                .when()
                .patch("https://restful-booker.herokuapp.com/booking/"+bookingId);

        JsonPath jsonResponse = response.jsonPath();

        Assertions.assertEquals("Sezgi",jsonResponse.get("firstname"));
        Assertions.assertEquals("KARA",jsonResponse.get("lastname"));

    }





}
