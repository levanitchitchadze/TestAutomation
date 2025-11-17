package core.steps.android;

import core.enums.Language;
import core.model.android.LoginPage;
import core.model.android.OTPPage;
import core.steps.common.ILoginSteps;
import core.utils.hellper.AppiumHelper;
import core.utils.messages.output.error.TestFailMessages;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginSteps extends AppiumHelper implements ILoginSteps {

    private final LoginPage loginPage = new LoginPage();
    private final OTPPage otpPage = new OTPPage();

    @Before
    public void setUp(Scenario scenario) {
        if (androidDriver == null) super.setUpForCucumber(scenario);
    }


    @Given("The user opened the application")
    public void itIsLoginPage() {
        loginPage.itIsLoginPage();
    }


    @And("{} notification permissions")
    public void notificationPermissions(boolean allow) {
        loginPage.acceptNotifications(allow);
    }


    public boolean wrongAttemptWindowIsDisplayed(boolean click) {
        return loginPage.wrongAttemptWindowIsDisplayed(click);
    }

    @And("Chose the {language} language")
    public void choseLanguage(Language languageName) {
        loginPage.choseLanguage(languageName);
    }

    //    User enters "<username>" and "<password>" and click submit button
    @When("User enters {string} and {string} and click submit button")
    public boolean login(String username, String password) {
        boolean performed = false;
        if (loginPage.itIsFastLoginPage()) performed = fastLogin(password);
        else performed = loginPage.login(username, password);
        return performed && checkLogin();

    }
    //


    public boolean fastLogin(String password) {

        return loginPage.fastLogin(password);
    }

    @Then("{string}")
    public void wrongAttemptWindowValidation(String chechDscription) {
        assert wrongAttemptWindowIsDisplayed(true) : TestFailMessages.WRONG_ATTEMPT_WINDOW_IS_NOT_DISPLAYED;
    }


    public boolean checkLogin() {
        return otpPage.itIsOTPPage();
    }
}


