package core.steps.android;

import core.config.android.PhysicalDeviceConfig;
import core.model.android.OTPPage;
import core.utils.hellper.AppiumHelper;
import core.utils.messages.input.MessageReader;
import core.utils.messages.output.error.TestFailMessages;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static core.data.android.OTPData.lastOTPCode;

@Slf4j
@NoArgsConstructor
public class OTPSteps extends AppiumHelper {


    private final OTPPage otpPage = new OTPPage();
    private String deviceSerialNumber;


    public OTPSteps(String deviceSerialNumber) {
        this.deviceSerialNumber = deviceSerialNumber;
    }


    @Given("Received OTP code")
    public String getLatestOTPCode() {
        PhysicalDeviceConfig physicalDevice = new PhysicalDeviceConfig();

        MessageReader messageReader = new MessageReader(deviceSerialNumber);
        return messageReader.getOTPCode(physicalDevice.getOTP_FILTER_TEXT(), physicalDevice.getOTP_SORT_COLUMN());
    }


    @When("Enter OTP code {string}")
    public void enterOTP(String otp) {
        otpPage.enterOtpCode(otp);
    }


    @When("Click resend button")
    public void resendOTPCode() {
        otpPage.resendOTPCode();
    }

    @Given("OTP page is open")
    public boolean itIsOTPPage() {
        return otpPage.itIsOTPPage();
    }


    public boolean wrongAttemptWindowIsDisplayed(boolean click) {
        return otpPage.wrongAttemptWindowIsDisplayed(click);
    }

    @Then("Should receive new OTP code")
    public void checkNewOTPCode(String latestOTP) {
        assert lastOTPCode.equals(latestOTP) : TestFailMessages.OTP_CODE_WAS_NOT_SEND;
        lastOTPCode = latestOTP;
    }
}
