package core.model.android;

import core.utils.hellper.AppiumHelper;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NotFoundException;

@Slf4j
public class OTPPage extends AppiumHelper {

    private final String OTP_INPUT = "com.icomvision.bsc.tbc:id/payment_authorization_content";
    private final String RESEND_BTN = "com.icomvision.bsc.tbc:id/flResendWrapper";
    private final String SUBMIT_BTN = "com.icomvision.bsc.tbc:id/btTwoFactorConfirm";
    private final String TITLE = "com.icomvision.bsc.tbc:id/tvTitle";

    public boolean itIsOTPPage() {
        return isDisplayed(TITLE) && isDisplayed(SUBMIT_BTN) && isDisplayed(OTP_INPUT);
    }

    public void enterOtpCode(String otpCode) {

        try {
            type(OTP_INPUT, otpCode);
            click(SUBMIT_BTN);
        } catch (NotFoundException nfe) {
            log.error("Can't find OTP code element: " + nfe.getMessage());
        }


    }

}
