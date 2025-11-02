package test.automation.app_test;

import io.github.cdimascio.dotenv.Dotenv;
import net.datafaker.Faker;
import net.datafaker.providers.base.Credentials;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pom.base.AppiumBaseTest;
import pom.data.test_object.app_data.Language;
import pom.data.test_object.app_data.LoginData;
import pom.module.app_page.LoginPage;
import pom.step.app_step.LoginStep;
import pom.util.messages.error.TestFailMessages;

//    There are login tests I want to run, each method has a meaningful name, so just read :d
public class LoginTest extends AppiumBaseTest {

    private LoginPage loginPage;
    private LoginStep loginSteps;
    private LoginData loginData;
    private Faker faker;
    private Credentials fakeCredentials;

    @BeforeClass
    void setUp() {
        Dotenv dotenv = Dotenv.load();
        System.out.println("Mobile APP Test");

        loginPage = new LoginPage();
        loginData = new LoginData();
        loginSteps = new LoginStep(loginPage);
        faker = new Faker();
        fakeCredentials = faker.credentials();

        loginData.setUsername(dotenv.get("username"));
        loginData.setPassword(dotenv.get("password"));

    }

    @BeforeClass(dependsOnMethods = {"setUp"})
    void closePopups() {
        loginSteps.notificationPermissions(true);
        loginSteps.choseLanguage(Language.GEO);
    }


    @Test(priority = 1)
    void loginWithIncorrectCredentials() {

        String username = fakeCredentials.username();
        String password = fakeCredentials.password(8, 20, true, true, true);

        boolean isLoginSuccessfully = loginSteps.login(username, password);
        assert !isLoginSuccessfully : TestFailMessages.LOGIN_WITH_INVALID_CREDENTIALS;
    }

    @Test(priority = 2)
    void loginSuccessfully() {

        boolean isLoginSuccessfully = loginSteps.login(loginData.getUsername(), loginData.getPassword());

        assert isLoginSuccessfully : TestFailMessages.LOGIN_SUCCESSFULLY;
    }


}
