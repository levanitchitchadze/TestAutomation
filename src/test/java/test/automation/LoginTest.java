package test.automation;

import core.base.IPlatformFactory;
import core.base.TestBase;
import core.data.app.Language;
import core.data.app.LoginData;
import core.factories.IApiFactory;
import core.factories.IAppFactory;
import core.factories.IWebFactory;
import core.steps.app.LoginSteps;
import core.utils.messages.error.TestFailMessages;
import io.github.cdimascio.dotenv.Dotenv;
import net.datafaker.Faker;
import net.datafaker.providers.base.Credentials;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//    There are login tests I want to run, each method has a meaningful name, so just read :d
public class LoginTest extends TestBase {


    private LoginSteps loginSteps;
    private LoginData loginData;
    private Faker faker;
    private Credentials fakeCredentials;
    private IPlatformFactory IPlatformFactory;

    @BeforeClass
    @Parameters({"platform"})
    void setUp(String platform) {
        Dotenv dotenv = Dotenv.load();

        switch (platform) {
            case "app":
                setUpApp();
            case "web":
                setUpWeb();
            case "Api":
                setUpApi();
        }


        loginData = new LoginData();
        loginSteps = new LoginSteps();
        faker = new Faker();
        fakeCredentials = faker.credentials();

        loginData.setUsername(dotenv.get("username"));
        loginData.setPassword(dotenv.get("password"));
    }

    private void setUpApp() {
        System.out.println("APP tests set up");
        IPlatformFactory = new IAppFactory();
    }

    private void setUpWeb() {
        System.out.println("Web tests set up");
        IPlatformFactory = new IWebFactory();

    }

    private void setUpApi() {
        System.out.println("API tests set up");
        IPlatformFactory = new IApiFactory();


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
