package com.sabre.interview.steps;

import com.google.gson.Gson;
import com.sabre.interview.model.University;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UniversityStepDefinitions {

    private static final String BASE_URL = "http://universities.hipolabs.com/";
    private static final Gson GSON = new Gson();
    private Response response;

    @Given("^University search API is accessible$")
    public void universitySearchApiIsAccessible() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.port = 80;
        get("/").then().statusCode(200);
    }

    @When("I search for universities with limit {int}")
    public void searchForUniversities(int limit) {
        RequestSpecification httpRequest = RestAssured.given();
        response = httpRequest.queryParam("limit", limit).get("/search");
    }

    @When("I search for universities by country {string}")
    public void searchForUniversitiesByCountry(String country) {
        RequestSpecification httpRequest = RestAssured.given();
        response = httpRequest.queryParam("country",country).get("/search");
    }

    @When("I search for universities by name {string}")
    public void searchForUniversitiesByName(String name) {
        RequestSpecification httpRequest = RestAssured.given();
        response = httpRequest.queryParam("name", name).get("/search");
    }

    @Then("the requests response will contain universities with country {string}")
    public void verifyResponseContainUniversitiesWithCountry(String expectedCountry) {
        assertEquals(200, response.getStatusCode(),
                String.format("Response should return 200 status, received status %s", response.getStatusCode()));
        String responseBody = response.body().asString();
        List<University> responseUniversities = Arrays.asList(GSON.fromJson(responseBody, University[].class));
        responseUniversities.forEach(university -> assertEquals(university.getCountry(), expectedCountry,
                String.format("University: %s should have country set to: %s, actual: %s", university, expectedCountry, university.getCountry())));
    }

    @Then("the requests response will contain universities containing name {string}")
    public void verifyResponseContainUniversitiesWithName(String expectedName) {
        assertEquals(200, response.getStatusCode());
        String responseBody = response.body().asString();
        List<University> responseUniversities = Arrays.asList(GSON.fromJson(responseBody, University[].class));
        responseUniversities.forEach(university -> assertTrue(StringUtils.containsAnyIgnoreCase(university.getName(), expectedName),
                String.format("University: %s should have name set to: %s, actual: %s", university, expectedName, university.getName())));
    }

    @Then("the requests response will contain {int} universities")
    public void verifyResponseContainUniversitiesWithName(int expectedUniversitiesCount) {
        assertEquals(200, response.getStatusCode());
        String responseBody = response.body().asString();
        List<University> responseUniversities = Arrays.asList(GSON.fromJson(responseBody, University[].class));
        assertEquals(responseUniversities.size(), expectedUniversitiesCount,
                String.format("Expected %d universities in response, actual: %d", expectedUniversitiesCount, responseUniversities.size()));
    }

}
