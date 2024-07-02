package com.hotelreservation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class _BaseTestForUpdate {


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
