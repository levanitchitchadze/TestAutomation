package test.automation.stepDefinition;

import core.base.TestBase;
import core.data.LoginData;
import core.enums.Language;
import core.steps.common.LoginSteps;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginDefinition extends TestBase {

    LoginSteps loginSteps;
    LoginData loginData;

    @Before
    public void setUp(Scenario scenario) {
        if (ctx == null) super.setUpForCucumber(scenario);
    }

    @Given("The user opened the application")
    public void The_user_opened_the_application() {


        loginSteps = ctx.getLoginSteps();
        loginData = new LoginData();

    }

    @Given("allow notification permissions")
    public void allow_notification_permissions() {

        loginSteps.notificationPermissions(true);
    }

    @Given("Chose the GEO language")
    public void chose_the_geo_language() {
        loginSteps.choseLanguage(Language.GEO);
    }

    @Given("User has {string} username and {string} password")
    public void user_has_username_and_password(String usernameType, String passwordType) {
        loginSteps.initCredentials(usernameType, passwordType);


    }

    @When("User enters credentials")
    public void user_enters_credentials() {
        loginSteps.login();
    }

    @Then("The wrong login attempt window {string}")
    public void the_wrong_login_attempt_window(String string) {
        boolean shouldBeDisplayed = string.contains("should be displayed");
        loginSteps.wrongAttemptWindowValidation(true, shouldBeDisplayed);
    }


}
