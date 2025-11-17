package core.steps.api;

import core.model.users.CreateUserRequest;
import core.module.api.UsersController;
import io.restassured.response.Response;

import static core.utils.regex.MatcherRegexes.EMAIL_VALIDATOR_REGEX;
import static org.hamcrest.Matchers.*;

public class UsersSteps {
    UsersController usersController = new UsersController();
    private Response users;

    private int pageNumber;

    private CreateUserRequest lastModifiedUserRequest;
    private Response lastModifiedUserResponse;
    private String modificationType = "create";

    public UsersSteps getUsers(int pageNumber) {
        this.pageNumber = pageNumber;
        users = usersController.getUsers(pageNumber);
        return this;
    }


    public UsersSteps createUser(CreateUserRequest createUserRequest) {
        this.lastModifiedUserRequest = createUserRequest;
        lastModifiedUserResponse = usersController.createUser(createUserRequest);
        modificationType = "create";

        return this;
    }

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


    public Response validateCreation(boolean success) {
        int expectedStatus = 200;
        if (modificationType.equals("create")) expectedStatus = 201;

        if (success) lastModifiedUserResponse.then()
                .statusCode(expectedStatus)
                .body("name", equalTo(lastModifiedUserRequest.getName()))
                .body("job", equalTo(lastModifiedUserRequest.getJob()));


        return lastModifiedUserResponse;
    }

    public UsersSteps updateUser(CreateUserRequest createUserRequest, String userId) {
        this.lastModifiedUserRequest = createUserRequest;
        lastModifiedUserResponse = usersController.updateUser(createUserRequest, userId);
        modificationType = "update";


        return this;
    }


    public UsersSteps updateUserPart(CreateUserRequest createUserRequest, String userId) {
        this.lastModifiedUserRequest = createUserRequest;
        lastModifiedUserResponse = usersController.updateUserPart(createUserRequest, userId);
        modificationType = "update";


        return this;
    }

    public void deleteUser(String userId) {
        usersController.deleteUser(userId).then().statusCode(204);
    }


}
