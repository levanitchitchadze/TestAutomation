package core.steps.android;

import core.config.android.PhysicalDeviceConfig;
import core.module.android.AndroidOTPPage;
import core.module.common.OTPPage;
import core.steps.common.OTPSteps;
import core.utils.hellper.AppiumHelper;
import core.utils.messages.input.MessageReader;
import core.utils.messages.output.error.TestFailMessages;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static core.data.OTPData.lastOTPCode;

@Slf4j
@NoArgsConstructor
public class AndroidOTPSteps extends AppiumHelper implements OTPSteps {


    private final AndroidOTPPage otpPage = new AndroidOTPPage();
    private String deviceSerialNumber;


    public AndroidOTPSteps(String deviceSerialNumber) {
        this.deviceSerialNumber = deviceSerialNumber;
    }

    public static OTPSteps getInstance(OTPPage page) {
        return new AndroidOTPSteps();
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
        return otpPage.wrongAttemptValidation(click);
    }

    @Then("Should receive new OTP code")
    public void checkNewOTPCode(String latestOTP) {
        assert lastOTPCode.equals(latestOTP) : TestFailMessages.OTP_CODE_WAS_NOT_SEND;
        lastOTPCode = latestOTP;
    }
}
