package utils;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import models.BaseModel;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.RestAssuredConfig.newConfig;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiClient {

    private static final String TOKEN = System.getProperty(Properties.API_TOKEN);
    private final RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    private RequestSpecification requestSpecification;

    public Response sendRequest(Method method, int expectedStatusCode, String url, String jsonSchema, Object... params) {
        ValidatableResponse response = given()
                .spec(requestSpecification)
                .request(method, url, params).prettyPeek()
                .then().log().status();
        if (!Objects.equals(jsonSchema, "")) {
            return response.body(matchesJsonSchemaInClasspath(jsonSchema))
                    .extract().response();
        }

        Response rs = response.extract().response();

        if (expectedStatusCode != -1) {
            rs = rs.then().statusCode(expectedStatusCode).extract().response();
        }

        return rs;
    }

    public Response sendRequest(Method method, int expectedStatusCode, BaseModel body, String url, String jsonSchema, Object... params) {
        ValidatableResponse response = given()
                .spec(requestSpecification)
                .body(body)
                .request(method, url, params).prettyPeek()
                .then().log().status();

        if (!Objects.equals(jsonSchema, "")) {
            return response.body(matchesJsonSchemaInClasspath(jsonSchema))
                    .extract().response();
        }

        Response rs = response.extract().response();

        if (expectedStatusCode != -1) {
            rs = rs.then().statusCode(expectedStatusCode).extract().response();
        }

        return rs;
    }

    public ApiClient build() {
        System.out.println(System.getProperty(Properties.BASE_API_URL));
        requestSpecification = requestSpecBuilder
                .setConfig(newConfig().encoderConfig(encoderConfig().defaultContentCharset(StandardCharsets.UTF_8)))
                .setBaseUri(System.getProperty(Properties.BASE_API_URL))
                .log(LogDetail.ALL)
                .addHeader("Authorization", "Bearer " + TOKEN)
                .addHeader("X-GitHub-Api-Version", "2022-11-28")
                .addFilter(new AllureRestAssured())
                .build();
        return this;
    }

    public ApiClient addQueryParam(String name, String value) {
        requestSpecBuilder.addQueryParam(name, value);
        return this;
    }

    public ApiClient addBody(String body) {
        requestSpecBuilder.setBody(body);
        return this;
    }

    public ApiClient addBody(Object body) {
        requestSpecBuilder.setBody(body);
        return this;
    }

    public ApiClient addJsonContentType() {
        requestSpecBuilder.addHeader("Content-Type", "application/json");
        return this;
    }

    public ApiClient addHeader(String header, String value) {
        requestSpecBuilder.addHeader(header, value);
        return this;
    }

}

