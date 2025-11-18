package core.steps.android;

import core.config.android.PhysicalDeviceConfig;
import core.module.android.AndroidOTPPage;
import core.module.common.OTPPage;
import core.steps.common.OTPSteps;
import core.utils.hellper.AppiumHelper;
import core.utils.messages.input.MessageReader;
import core.utils.messages.output.error.TestFailMessages;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.openqa.selenium.NotFoundException;

import static core.data.OTPData.lastOTPCode;
import static core.utils.messages.output.cucumber.ThenMessages.SHOULD_NOT_DISPLAYED_WRONG_ATTEMPT_WINDOW;

@Slf4j
@NoArgsConstructor
public class AndroidOTPSteps extends AppiumHelper implements OTPSteps {


    private final AndroidOTPPage otpPage = new AndroidOTPPage();
    private String deviceSerialNumber;

    private Faker faker = new Faker();


    public AndroidOTPSteps(String deviceSerialNumber) {
        this.deviceSerialNumber = deviceSerialNumber;
    }

    public static OTPSteps getInstance(OTPPage page) {
        return new AndroidOTPSteps();
    }

    public String getLatestOTPCode() {
        PhysicalDeviceConfig physicalDevice = new PhysicalDeviceConfig();

        MessageReader messageReader = new MessageReader(deviceSerialNumber);
        return messageReader.getOTPCode(physicalDevice.getOTP_FILTER_TEXT(), physicalDevice.getOTP_SORT_COLUMN());
    }


    public void enterOTP(boolean validOtp) {

        otpPage.enterOtpCode(validOtp ? getLatestOTPCode() : faker.number().digits(4));

    }

    public void enterOTP(String otp) {
        otpPage.enterOtpCode(otp);
    }


    public void resendOTPCode() {
        try {
            otpPage.resendOTPCode();

        } catch (NotFoundException nfe) {

            throw new RuntimeException("Can't found OTP resend button: " + nfe.getMessage());
        } finally {
            wrongOTPAttemptWindowCheck(SHOULD_NOT_DISPLAYED_WRONG_ATTEMPT_WINDOW);

        }

    }


    public void itIsOTPPage() {
        waitTimeOut(5);
        assert otpPage.itIsOTPPage() : TestFailMessages.ITS_NOT_CORRECT_PAGE;
    }


    private void wrongOTPAttemptWindowCheck(String stepDescription) {
        boolean shouldDisplayed = stepDescription.contains("should be displayed");

        assert !wrongAttemptWindowIsDisplayed(true) || !shouldDisplayed : TestFailMessages.WRONG_ATTEMPT_WINDOW_DISPLAYED;
    }


    @Override
    public void wrongAttemptWindowValidation(boolean click, boolean shouldBeDisplayed) {

        assert !(wrongAttemptWindowIsDisplayed(click) ^ shouldBeDisplayed) : TestFailMessages.WRONG_ATTEMPT_WINDOW_DISPLAYED;


    }

    @Override
    public void wrongAttemptWindowValidation(boolean click) {
        assert wrongAttemptWindowIsDisplayed(click) : TestFailMessages.WRONG_ATTEMPT_WINDOW_DISPLAYED;

    }

    public boolean wrongAttemptWindowIsDisplayed(boolean click) {
        return otpPage.wrongAttemptValidation(click);
    }

    public void checkNewOTPCode() {
        if (lastOTPCode == null) return;
        assert lastOTPCode.equals(getLatestOTPCode()) : TestFailMessages.OTP_CODE_WAS_NOT_SEND;
        lastOTPCode = getLatestOTPCode();
    }
}
