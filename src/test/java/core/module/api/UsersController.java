package core.module.api;

import core.enums.HTTP;
import core.model.users.CreateUserRequest;
import core.utils.api.APIRequestBuilder;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UsersController {
    private String GET_USERS = "/users";
    private String CREATE_USER = "/users";
    private String UPDATE_USER = "/users";

    public Response getUsers(int pageNumber) {

        APIRequestBuilder apiRequestBuilder = new APIRequestBuilder()
                .method(HTTP.GET)
                .uri(GET_USERS)
                .query("page", pageNumber);

        Response response = apiRequestBuilder.send();
        log.info("\nusers api response: ");
        response.body().prettyPrint();
        return response;


    }

    public Response createUser(CreateUserRequest createUserRequest) {
        APIRequestBuilder apiRequestBuilder = new APIRequestBuilder()
                .method(HTTP.POST)
                .uri(CREATE_USER)
                .body(createUserRequest);

        Response response = apiRequestBuilder.send();
        log.info("\nCreate user api response: " + response.statusCode());
        response.body().prettyPrint();
        return response;
    }

    public Response updateUser(CreateUserRequest createUserRequest, String userId) {
        APIRequestBuilder apiRequestBuilder = new APIRequestBuilder()
                .method(HTTP.PUT)
                .uri(UPDATE_USER + "/" + userId)
                .body(createUserRequest);

        Response response = apiRequestBuilder.send();
        log.info("\nUpdate user api response: " + response.statusCode());
        response.body().prettyPrint();
        return response;
    }


    public Response updateUserPart(CreateUserRequest createUserRequest, String userId) {
        APIRequestBuilder apiRequestBuilder = new APIRequestBuilder()
                .method(HTTP.PATCH)
                .uri(UPDATE_USER + "/" + userId)
                .body(createUserRequest);

        Response response = apiRequestBuilder.send();
        log.info("\nPartial update user api response: " + response.statusCode());
        response.body().prettyPrint();
        return response;
    }


    public Response deleteUser(String userId) {

        APIRequestBuilder apiRequestBuilder = new APIRequestBuilder()
                .method(HTTP.DELETE)
                .uri(UPDATE_USER + "/" + userId);

        Response response = apiRequestBuilder.send();
        log.info("\nDelete user status code: " + response.statusCode());
        response.body().prettyPrint();
        return response;
    }

}
