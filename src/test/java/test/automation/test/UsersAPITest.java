package test.automation.test;


import core.base.TestBase;
import core.data.TestDataClass;
import core.data.UsersData;
import core.model.users.CreateUserRequest;
import core.steps.api.UsersSteps;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//It's not very handy to connect ReqRes.in api to TBC mobile bank application so I decided to create this class
//just for different steps and controllers
public class UsersAPITest extends TestBase {

    private UsersSteps usersSteps;
    private UsersData usersData;
    private TestDataClass testDataClass;
    private CreateUserRequest createUserRequest;
    private String userId;

    @BeforeClass
    void setUpUserAPITest() {
        usersSteps = new UsersSteps();
        usersData = new UsersData();
        testDataClass = new TestDataClass();
        createUserRequest = new CreateUserRequest();

        usersSteps.checkUserPermissions();
    }

    @Test
    void getUsers() {

        for (int pageNumber : usersData.getPAGE_NUMBERS()) {
            usersSteps.getUsers(pageNumber).validate(pageNumber < 3);
        }

    }


    @Test
    void createUsers() {

        createUserRequest.setName(testDataClass.getFaker().name().fullName());
        createUserRequest.setJob(testDataClass.getFaker().job().position());

        Response response = usersSteps.createUser(createUserRequest).validateCreation(true);
        userId = response.jsonPath().get("id");
    }


    @Test
    void updateUsers() {

        createUserRequest.setName(testDataClass.getFaker().name().fullName());
        createUserRequest.setJob(testDataClass.getFaker().job().position());

        usersSteps.updateUser(createUserRequest, userId).validateCreation(true);

    }


    @Test
    void userPartialUpdate() {

        createUserRequest.setName(testDataClass.getFaker().name().fullName());

        usersSteps.updateUserPart(createUserRequest, userId).validateCreation(true);

    }


    @Test
    void deleteUser() {


        usersSteps.deleteUser(userId);

    }


}
