package org.rtu.jtarasova_191rdb176;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefinitions {

    public static final String API_URL = "https://gorest.co.in";
    public static final String ACCESS_TOKEN = "c759ab45473dcfd5bdac438dc8de960fcd3b24ecbe9c967323e27a662dff613d";

    private Response response;
    private RequestSpecification requestSpecification;

    @Given("^request body:$")
    public void requestBody(String body) {
        requestSpecification = given()
                .baseUri(API_URL)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .body(body);
    }

    @When("user performs {string} request to {string} using {string}")
    public void userPerformsRequestTo(String method, String path, String apiType) {
        response = requestSpecification.when().request(method, path);
        System.out.println("Response is:");
        response.body().prettyPrint();
    }

    @Then("response contains status {int}")
    public void responseContainsStatus(int status) {
        response.then().statusCode(status);
    }

    @And("response body id is not null")
    public void responseBodyIdIsNotNull() {
        response.then().body("id", Matchers.notNullValue());
    }

    @And("response body name is {string}")
    public void responseBodyNameIs(String name) {
        response.then().body("name", Matchers.equalTo(name));
    }

    @And("response body email is {string}")
    public void responseBodyEmailIs(String email) {
        response.then().body("email", Matchers.equalTo(email));
    }

    @And("response body gender is {string}")
    public void responseBodyGenderIs(String gender) {
        response.then().body("gender", Matchers.equalTo(gender));
    }

    @And("response body status is {string}")
    public void responseBodyStatusIs(String status) {
        response.then().body("status", Matchers.equalTo(status));
    }

    @And("response body contains error field value {string}")
    public void responseBodyContainsErrorFieldValue(String field) {
        response.then().body("[0].field", Matchers.equalTo(field));
    }

    @And("error message {string}")
    public void errorMessage(String message) {
        response.then().body("[0].message", Matchers.equalTo(message));
    }
}
