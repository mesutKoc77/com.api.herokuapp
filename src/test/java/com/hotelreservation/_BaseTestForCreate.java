package com.hotelreservation;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class _BaseTestForCreate {

    public String createBookingBody(String checkin, String checkout, String firstname,
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
        return body.toString();
    }

    public JsonPath createBookingResponseBody(String checkin, String checkout, String firstname,
                                              String lastname, int totalprice, Boolean depositpaid,
                                              String additionalneeds) {
        String requestBody = createBookingBody(checkin, checkout, firstname, lastname, totalprice, depositpaid, additionalneeds);

        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("https://restful-booker.herokuapp.com/booking");

        response.then().statusCode(200);
        return response.jsonPath();
    }
}
