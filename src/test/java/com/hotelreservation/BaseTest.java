package com.hotelreservation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected int bookingId;

    protected Response createBooking(String checkin, String checkout, String firstname,
                                     String lastname, int totalprice, Boolean depositpaid,
                                     String additionalneeds) {

        JSONObject requestBody = createBookingBody(checkin, checkout, firstname, lastname, totalprice, depositpaid, additionalneeds);

        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .post("https://restful-booker.herokuapp.com/booking");

        response.then().statusCode(200);
        bookingId=response.jsonPath().get("bookingid");
        return response;
    }

    protected JSONObject createBookingBody(String checkin, String checkout, String firstname,
                                           String lastname, int totalprice, Boolean depositpaid,
                                           String additionalneeds) {
        JSONObject body = new JSONObject();
        JSONObject jsonBDates = new JSONObject();
        jsonBDates.put("checkin", checkin);
        jsonBDates.put("checkout", checkout);
        body.put("firstname", firstname);
        body.put("lastname", lastname);
        body.put("totalprice", totalprice);
        body.put("depositpaid", depositpaid);
        body.put("bookingdates", jsonBDates);
        body.put("additionalneeds", additionalneeds);
        return body;
    }



    protected String getAuthToken() {
        JSONObject body = new JSONObject();
        body.put("username", "admin");
        body.put("password", "password123");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .post("https://restful-booker.herokuapp.com/auth");

        response.then().statusCode(200);
        return response.jsonPath().getString("token");
    }
}
