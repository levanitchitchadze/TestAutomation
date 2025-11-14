package test.automation;

import core.base.TestBase;
import core.data.android.LoginData;
import core.enums.Language;
import core.steps.android.LoginSteps;
import core.utils.messages.output.error.TestFailMessages;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//    There are login tests I want to run, each method has a meaningful name, so just read :d
public class LoginTest extends TestBase {


    private LoginSteps loginSteps;
    private LoginData loginData;

    @BeforeClass
    void setUpLoginTest() {


        loginData = new LoginData();
        loginSteps = new LoginSteps();

    }


    @BeforeClass(dependsOnMethods = {"setUpLoginTest"})
    void closePopups() {
        loginSteps.notificationPermissions(true);
        loginSteps.choseLanguage(Language.GEO);
    }


    @Test(groups = {"loginNegative", "login"})
    void loginWithIncorrectUsername() {
        boolean isLoginSuccessfully = loginSteps.login(loginData.getIncorrectUsername(), loginData.getPassword());
        loginSteps.wrongAttemptWindowIsDisplayed();
//        assert !loginSteps.wrongAttemptWindowIsDisplayed() : TestFailMessages.WRONG_ATTEMPT_WINDOW_IS_NOT_DISPLAYED;
//        assert isLoginSuccessfully : TestFailMessages.LOGIN_WITH_INVALID_CREDENTIALS;
    }

    @Test(groups = {"loginNegative", "login"})
    void loginWithIncorrectPassword() {
        boolean isLoginSuccessfully = loginSteps.login(loginData.getUsername(), loginData.getIncorrectPassword());
        loginSteps.wrongAttemptWindowIsDisplayed();
//        assert !loginSteps.wrongAttemptWindowIsDisplayed() : TestFailMessages.WRONG_ATTEMPT_WINDOW_IS_NOT_DISPLAYED;
//        assert isLoginSuccessfully : TestFailMessages.LOGIN_WITH_INVALID_CREDENTIALS;
    }

    @Test(groups = {"loginNegative", "login"})
    void loginWithIncorrectCredentials() {
        boolean isLoginSuccessfully = loginSteps.login(loginData.getIncorrectUsername(), loginData.getIncorrectPassword());
        loginSteps.wrongAttemptWindowIsDisplayed();
//        assert !loginSteps.wrongAttemptWindowIsDisplayed() : TestFailMessages.WRONG_ATTEMPT_WINDOW_IS_NOT_DISPLAYED;
//        assert isLoginSuccessfully : TestFailMessages.LOGIN_WITH_INVALID_CREDENTIALS;
    }

    @Test(dependsOnGroups = {"loginNegative"}, groups = {"login"})
    void loginSuccessfully() {

        boolean isLoginSuccessfully = loginSteps.login(loginData.getUsername(), loginData.getPassword());
        assert isLoginSuccessfully : TestFailMessages.LOGIN_SUCCESSFULLY;
    }


}
