package com.ramkrishna.restapitests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected RequestSpecification specification;

    @BeforeMethod
    public void specificationSetup(){
        specification = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com/").build();
    }

    public Response createBooking(){

        JSONObject data = new JSONObject();

        data.put("firstname", "JimFirst");
        data.put("lastname", "JimLast");
        data.put("totalprice", 100);
        data.put("depositpaid", false);

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2024-01-01");
        bookingdates.put("checkout", "2024-01-03");
        data.put("bookingdates", bookingdates);
        data.put("additionalneeds", "lunch");

        Response response = RestAssured.given().spec(specification).contentType(ContentType.JSON).body(data.toString())
                .post("/booking");
        response.print();

        return response;

    }


}
