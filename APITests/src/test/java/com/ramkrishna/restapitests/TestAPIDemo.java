package com.ramkrishna.restapitests;

import groovyjarjarpicocli.CommandLine;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;


public class TestAPIDemo extends BaseTest{

    @Test
    public void pingTest(){

        given().spec(specification).when().get("/ping").then().log().all().assertThat().statusCode(201);
    }


    @Test
    public void getBookingIdsTest(){
        System.out.println("This test is related to get list of bookings");
        Response response =  given().spec(specification).when().get("/booking");
        response.print();

        List<Integer> bookingIds = response.jsonPath().getList("bookingid");

        System.out.println(bookingIds.size());
        Assert.assertFalse(bookingIds.isEmpty(), "Booking list is empty");
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not matching");


    }

    @Test
    public void getBookingByIdsTest(){
        System.out.println("This test is get booking id by id number");
        Response response = given().spec(specification).when().get("/booking/8");;
        response.print();
        response.prettyPeek();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not matching");

//        SoftAssert softassert = new SoftAssert();
//
//        String actualFirstName = response.jsonPath().getString("firstname");
//        String actualAdditionalBreakfast = response.jsonPath().getString("additionalneeds");
//
//        softassert.assertEquals(actualFirstName, "Eric","First name is not matching");
//        softassert.assertEquals(actualAdditionalBreakfast, "Breakfast", "additionalneeds is mismatch");
//        softassert.assertAll();

    }

    @Test
    public void createBookingTest(){

//        JSONObject data = new JSONObject();
//
//        data.put("firstname", "JimFirst");
//        data.put("lastname", "JimLast");
//        data.put("totalprice", 100);
//        data.put("depositpaid", false);
//
//        JSONObject bookingdates = new JSONObject();
//        bookingdates.put("checkin", "2024-01-01");
//        bookingdates.put("checkout", "2024-01-03");
//        data.put("bookingdates", bookingdates);
//        data.put("additionalneeds", "lunch");
//
//        Response response = given().spec(specification).contentType(ContentType.JSON).body(data.toString())
//                .post("/booking");

        Response response = createBooking();

    }

    @Test
    public void updateBookingTest(){

        Response response = createBooking();
        int bookingId = response.jsonPath().getInt("bookingid");


        /*
        * Updating the data using put request
        * */

        JSONObject updateData = new JSONObject();

        updateData.put("firstname", "JimFirstUpdated");
        updateData.put("lastname", "JimLastUpdated");
        updateData.put("totalprice", 101);
        updateData.put("depositpaid", true);

        JSONObject updatebookingdates = new JSONObject();
        updatebookingdates.put("checkin", "2024-01-01");
        updatebookingdates.put("checkout", "2024-01-03");
        updateData.put("bookingdates", updatebookingdates);
        updateData.put("additionalneeds", "lunch");

        Response updateResponse = given().spec(specification).contentType(ContentType.JSON)
                .auth().preemptive().basic("admin", "password123")
                .body(updateData.toString())
                .put("/booking/" +bookingId );

        updateResponse.print();

    }




    @Test
    public void partialUpdateBookingTest(){

        Response response = createBooking();
        int bookingId = response.jsonPath().getInt("bookingid");


        /*
         * Updating specific data using patch request
         * */

        JSONObject updateData = new JSONObject();

        updateData.put("firstname", "JimFirstUpdatedOnly");
        updateData.put("lastname", "JimLastUpdatedOnly");
        updateData.put("additionalneeds", "dinner");

        Response updateResponse = given().spec(specification).contentType(ContentType.JSON)
                .auth().preemptive().basic("admin", "password123")
                .body(updateData.toString())
                .patch("/booking/" +bookingId );

        updateResponse.print();

    }

    @Test
    public void deleteBookingTest(){
        Response response = createBooking();
        int bookingId = response.jsonPath().getInt("bookingid");

        /*
         * Deleting the data using delete request
         * */


        Response deleteResponse = given().spec(specification)
                .auth().preemptive().basic("admin", "password123")
                .delete("booking/" +bookingId );

        deleteResponse.print();
        System.out.println( deleteResponse.statusCode());

    }
}
