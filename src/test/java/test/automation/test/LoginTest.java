package test.automation.test;

import core.base.TestBase;
import core.data.LoginData;
import core.enums.Language;
import core.model.users.LoginModel;
import core.steps.android.AndroidLoginSteps;
import core.utils.messages.output.error.TestFailMessages;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


//    There are login tests I want to run, each method has a meaningful name, so just read :d
public class LoginTest extends TestBase {


    private AndroidLoginSteps loginSteps;
    private LoginData loginData;
    private LoginModel loginModel;

    @BeforeClass
    void setUpLoginTest() {
        loginData = new LoginData();
        loginSteps = new AndroidLoginSteps();

    }


    @Test()
    void checkApplicationOpened() {
        loginSteps.itIsLoginPage();
    }


    @Test(dependsOnMethods = {"checkApplicationOpened"})
    void closePopups() {
//        loginSteps.notificationPermissions(true); //For old version Tbc bank :)
        loginSteps.choseLanguage(Language.GEO);
    }


    @Test(dependsOnMethods = {"closePopups"}, groups = {"loginNegative", "login"})
    void loginWithIncorrectUsername() {

        loginSteps.setUsername(loginData.getIncorrectUsername());
        loginSteps.setPassword(loginData.getPassword());
        loginSteps.login();
        loginSteps.wrongAttemptWindowValidation(true);

    }

    @Test(dependsOnMethods = {"closePopups"}, groups = {"loginNegative", "login"})
    void loginWithIncorrectPassword() {
        //User may lock so I use incorrect username :d

        loginSteps.setUsername(loginData.getIncorrectUsername());
        loginSteps.setPassword(loginData.getIncorrectPassword());
        loginSteps.login();
        loginSteps.wrongAttemptWindowValidation(true);

    }

    @Test(dependsOnMethods = {"closePopups"}, groups = {"loginNegative", "login"})
    void loginWithIncorrectCredentials() {
        loginSteps.setUsername(loginData.getIncorrectUsername());
        loginSteps.setPassword(loginData.getIncorrectPassword());
        loginSteps.login();
        loginSteps.wrongAttemptWindowValidation(true);

    }

    @Test(dependsOnGroups = {"loginNegative"}, groups = {"login"}, alwaysRun = true)
    void loginSuccessfully() {
        loginSteps.setUsername(loginData.getUsername());
        loginSteps.setPassword(loginData.getPassword());
        boolean isLoginSuccessfully = loginSteps.login();

        assert isLoginSuccessfully : TestFailMessages.LOGIN_SUCCESSFULLY;
    }


}
