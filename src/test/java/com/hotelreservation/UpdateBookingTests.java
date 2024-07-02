package com.hotelreservation;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UpdateBookingTests extends _BaseTestForUpdate {


    @Test
    public void testUpdate(){
        _BaseTestForCreate create = new _BaseTestForCreate();

        JsonPath bookingResponseBody =create.createBookingResponseBody("2024-10-16","2024-10-25","Ahmet","Nadir",500,true,"no need");
        int bookingid = bookingResponseBody.get("bookingid");
        bookingResponseBody.prettyPrint();

        String token = getAuthToken();

        String updatedBody = create.createBookingBody("2024-10-18","2024-10-20","Güncel","Doganay",600,false,"NeeD");

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(updatedBody)
                .log().all()
                .put("https://restful-booker.herokuapp.com/booking/" + bookingid);

        response.then().statusCode(200);

        JsonPath jsonResponse = response.jsonPath();
        Assertions.assertEquals("Güncel", jsonResponse.get("firstname"));

        assertEquals("Doganay", jsonResponse.getString("lastname"));
        assertEquals(600, jsonResponse.getInt("totalprice"));
        assertFalse(jsonResponse.getBoolean("depositpaid"));
        assertEquals("2024-10-18", jsonResponse.getString("bookingdates.checkin"));
        assertEquals("2024-10-20", jsonResponse.getString("bookingdates.checkout"));
        assertEquals("NeeD", jsonResponse.getString("additionalneeds"));

    }


}
