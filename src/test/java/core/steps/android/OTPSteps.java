package core.steps.android;

import core.config.android.PhysicalDeviceConfig;
import core.model.android.OTPPage;
import core.utils.hellper.AppiumHelper;
import core.utils.messages.input.MessageReader;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OTPSteps extends AppiumHelper {


    private final String deviceSerialNumber;
    private final OTPPage otpPage = new OTPPage();


    public OTPSteps(String deviceSerialNumber) {
        this.deviceSerialNumber = deviceSerialNumber;
    }


    public String getLatestOTPCode() {
        PhysicalDeviceConfig physicalDevice = new PhysicalDeviceConfig();

        MessageReader messageReader = new MessageReader(deviceSerialNumber);
        String otp = messageReader.getOTPCode(physicalDevice.getOTP_FILTER_TEXT(), physicalDevice.getOTP_SORT_COLUMN());

        log.info("OTP code: " + otp);

        return otp;
    }


    public void enterOTP(String otp) {
        otpPage.enterOtpCode(otp);
    }


    public void resendOTPCode() {
        otpPage.resendOTPCode();
    }

    public boolean itIsOTPPage() {
        return otpPage.itIsOTPPage();
    }


    public boolean wrongAttemptWindowIsDisplayed(boolean click) {
        return otpPage.wrongAttemptWindowIsDisplayed(click);
    }
}
