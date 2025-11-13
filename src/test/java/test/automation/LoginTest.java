package test.automation;

import core.base.IPlatformAbstractFactory;
import core.base.TestBase;
import core.data.android.LoginData;
import core.enums.Language;
import core.steps.android.LoginSteps;
import core.utils.messages.error.TestFailMessages;
import net.datafaker.Faker;
import net.datafaker.providers.base.Credentials;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//    There are login tests I want to run, each method has a meaningful name, so just read :d
public class LoginTest extends TestBase {


    private LoginSteps loginSteps;
    private LoginData loginData;
    private Faker faker;
    private Credentials fakeCredentials;
    private IPlatformAbstractFactory iPlatformFactory;

    @BeforeClass
    void setUpLoginTest() {


        loginData = new LoginData();
        loginSteps = new LoginSteps();
        faker = new Faker();
        fakeCredentials = faker.credentials();

    }


    @BeforeClass(dependsOnMethods = {"setUpLoginTest"})
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
