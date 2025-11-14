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


    private String getLatestOTPCode() {
        PhysicalDeviceConfig physicalDevice = new PhysicalDeviceConfig();

        MessageReader messageReader = new MessageReader(deviceSerialNumber, appiumHelper);
        return messageReader.getOTPCode(physicalDevice.getOTP_FILTER_TEXT(), physicalDevice.getOTP_SORT_COLUMN());
    }


    public void enterOTP() {
        String otp = getLatestOTPCode();
        otpPage.enterOtpCode(otp);
        log.info("OTP code: " + otp);
        waitTimeOut(100);
    }

    public boolean itIsOTPPage() {
        return otpPage.itIsOTPPage();
    }
}
