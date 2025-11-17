package test.automation;

import core.base.TestBase;
import core.data.android.LoginData;
import core.enums.Language;
import core.steps.android.LoginSteps;
import core.utils.messages.output.error.TestFailMessages;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static core.utils.messages.output.cucumber.ThenMessages.WRONG_ATTEMPT_WINDOW_SHOULD_BE_DISPLAYED;


//    There are login tests I want to run, each method has a meaningful name, so just read :d
public class LoginTest extends TestBase {


    private LoginSteps loginSteps;
    private LoginData loginData;

    @BeforeClass
    void setUpLoginTest() {
        loginData = new LoginData();
        loginSteps = new LoginSteps();

    }


    @Test()
    void checkApplicationOpened() {
        loginSteps.itIsLoginPage();
    }


    @Test(dependsOnMethods = {"checkApplicationOpened"})
    void closePopups() {
//        loginSteps.notificationPermissions(true); //For old version Tbc bank :)
//        loginSteps.confirmStart();
//        loginSteps.enterMobileNumber(loginData.getMobileNumber());
        loginSteps.choseLanguage(Language.GEO);
    }


    @Test(dependsOnMethods = {"closePopups"}, groups = {"loginNegative", "login"})
    void loginWithIncorrectUsername() {

        loginSteps.login(loginData.getIncorrectUsername(), loginData.getPassword());
        loginSteps.wrongAttemptWindowValidation(WRONG_ATTEMPT_WINDOW_SHOULD_BE_DISPLAYED);

    }

    @Test(dependsOnMethods = {"closePopups"}, groups = {"loginNegative", "login"})
    void loginWithIncorrectPassword() {
        //User may lock so I use incorrect username :d
        loginSteps.login(loginData.getIncorrectUsername(), loginData.getIncorrectPassword());
        loginSteps.wrongAttemptWindowValidation(WRONG_ATTEMPT_WINDOW_SHOULD_BE_DISPLAYED);

    }

    @Test(dependsOnMethods = {"closePopups"}, groups = {"loginNegative", "login"})
    void loginWithIncorrectCredentials() {

        loginSteps.login(loginData.getIncorrectUsername(), loginData.getIncorrectPassword());
        loginSteps.wrongAttemptWindowValidation(WRONG_ATTEMPT_WINDOW_SHOULD_BE_DISPLAYED);

    }

    @Test(dependsOnGroups = {"loginNegative"}, groups = {"login"}, alwaysRun = true)
    void loginSuccessfully() {

        boolean isLoginSuccessfully = loginSteps.login(loginData.getUsername(), loginData.getPassword());

        assert isLoginSuccessfully : TestFailMessages.LOGIN_SUCCESSFULLY;
    }


}
