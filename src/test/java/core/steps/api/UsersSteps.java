package core.steps.api;

import core.base.TestBase;
import core.model.users.CreateUserRequest;
import core.module.api.UsersController;
import core.utils.api.APIRequestBuilder;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static core.utils.regex.MatcherRegexes.EMAIL_VALIDATOR_REGEX;
import static org.hamcrest.Matchers.*;

@Slf4j
public class UsersSteps extends TestBase {
    UsersController usersController = new UsersController();
    private Response users;

    private int pageNumber;

    private CreateUserRequest lastModifiedUserRequest;
    private Response lastModifiedUserResponse;
    private String modificationType = "create";

    //I wish, I could use @Before in TestBase class but cucumber doesn't like it
    @Before
    public void setUpUsersSteps(Scenario scenario) {
        System.out.println(scenario.getName());
        if (APIRequestBuilder.requestSpecification == null) super.setUpForCucumber(scenario);
    }

    @Given("User have right permissions")
    public void checkUserPermissions() {
//        TODO: create user permissions validation method and move to different class
        log.info("Add permissions endpoint or database user to check user permissions");
    }

    @When("Send api-users GET request")
    public UsersSteps getUsers(int pageNumber) {
        this.pageNumber = pageNumber;
        users = usersController.getUsers(pageNumber);
        return this;
    }


    @When("Send api-users POST request")
    public UsersSteps createUser(CreateUserRequest createUserRequest) {
        this.lastModifiedUserRequest = createUserRequest;
        lastModifiedUserResponse = usersController.createUser(createUserRequest);
        modificationType = "create";

        return this;
    }


    @When("Send api-users PUT request")
    public UsersSteps updateUser(CreateUserRequest createUserRequest, String userId) {
        this.lastModifiedUserRequest = createUserRequest;
        lastModifiedUserResponse = usersController.updateUser(createUserRequest, userId);
        modificationType = "update";


        return this;
    }

    @When("Send api-users PATCH request")
    public UsersSteps updateUserPart(CreateUserRequest createUserRequest, String userId) {
        this.lastModifiedUserRequest = createUserRequest;
        lastModifiedUserResponse = usersController.updateUserPart(createUserRequest, userId);
        modificationType = "update";


        return this;
    }

    @When("Send api-users DELETE request")
    @Then("User record deleted")
    public void deleteUser(String userId) {
        usersController.deleteUser(userId).then().statusCode(204);
    }


    @Then("User gets users list")
    public void validate(boolean success) {

        if (success) users.then()
                .statusCode(200)
                .body("page", equalTo(pageNumber))
                .body("data[0].id", notNullValue())
                .body("data[0].email", matchesPattern(EMAIL_VALIDATOR_REGEX))
                .body("data[0].first_name", notNullValue())
                .body("data[0].last_name", notNullValue())
                .body("data[0].avatar", containsString("https://reqres.in/img/faces"));

    }


    @Then("User record created")
    @Then("User record updated")
    public Response validateCreation(boolean success) {
        int expectedStatus = 200;
        if (modificationType.equals("create")) expectedStatus = 201;

        if (success) lastModifiedUserResponse.then()
                .statusCode(expectedStatus)
                .body("name", equalTo(lastModifiedUserRequest.getName()))
                .body("job", equalTo(lastModifiedUserRequest.getJob()));


        return lastModifiedUserResponse;
    }

}
