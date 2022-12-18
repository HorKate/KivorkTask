package task2;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import runner.ApiTestRunner;


import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationInfoTest extends ApiTestRunner {
    private static ResponseSpecification responseSpec;
    private static RequestSpecification requestSpec;
    private static final double TOLERANCE = 0.01;

    @BeforeAll
    public static void createRequestSpecification() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://api.ipstack.com/")
                .build();
    }

    @BeforeAll
    public static void createResponseSpecification() {
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(JSON)
                .build();
    }

    @Test
    public void validateStatusCodeAndContentType() {
        given()
                .spec(requestSpec)
                .when()
                .get(IP_ADDRESS + "?access_key=" + DEFAULT_USER_TOKEN)
                .then()
                .spec(responseSpec);
    }

    @Test
    public void validateLatitudeAmdLongitudeForLocalExternalIpAddress() {
        var response = given()
                .spec(requestSpec)
                .when()
                .get(IP_ADDRESS + "?access_key=" + DEFAULT_USER_TOKEN);
        var locationInfo = response
                .getBody()
                .as(LocationInfo.class);
        var latitude = locationInfo.getLatitude();
        var longitude = locationInfo.getLongitude();
        var ipAddress = locationInfo.getIpAddress();

        assertEquals(49.42, latitude, TOLERANCE, "Incorrect latitude for IP: " + ipAddress);
        assertEquals(27.0, longitude, TOLERANCE, "Incorrect longitude for IP: " + ipAddress);
    }
}
