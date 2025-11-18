package test.automation.stepDefinition;

import core.base.TestBase;
import core.data.OTPData;
import core.steps.common.OTPSteps;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.datafaker.Faker;

import static core.data.OTPData.lastOTPCode;

public class OTPDefinition extends TestBase {
    private OTPSteps otpSteps;
    private OTPData otpData;
    private Faker faker;

    @Before
    public void setUp(Scenario scenario) {
        if (ctx == null) super.setUpForCucumber(scenario);

        otpSteps = ctx.getOtpSteps();
        otpData = new OTPData();
        faker = new Faker();

    }

    @Given("OTP page is open")
    public void otp_page_is_open() {

        otpSteps.itIsOTPPage();


    }

    @Given("Received OTP code")
    public void received_otp_code() {

        lastOTPCode = otpSteps.getLatestOTPCode();
    }


//    @When("Click resend button")
//    public void click_resend_button() {
//        otpSteps.resendOTPCode();
//        lastOTPCode = otpSteps.getLatestOTPCode();
//    }


    @When("Enter {string} OTP")
    public void enter_otp(String otpType) {
        if (otpType.equals("invalid")) {
            otpSteps.enterOTP(faker.number().digits(4));
        }
        otpSteps.enterOTP(otpSteps.getLatestOTPCode());

    }

    @Then("The wrong OTP attempt window {string}")
    public void the_wrong_otp_attempt_window(String string) {
        boolean shouldBeDisplayed = string.contains("should be displayed");

        otpSteps.wrongAttemptWindowValidation(true, shouldBeDisplayed);
    }

    @Then("Should receive new OTP code")
    public void should_receive_new_otp_code() {
        otpSteps.checkNewOTPCode();

    }


}
