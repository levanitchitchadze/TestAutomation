package pom.step.app_step;
import io.cucumber.java.en.*;
import test.automation.base.AppiumBase;

public class LoginSteps extends AppiumBase {

    @Given("App is Launched")
    public void launchApp()throws Exception{
        startDriver();
    }

    @When("I enter valid credentials")
    public void enterCredentials() {
        System.out.println("Entering username and password");
    }

    @Then("I should see the home screen")
    public void homeScreenIsVisible(){
        System.out.println("verifying home screen...");
        quitDriver();
    }

}
