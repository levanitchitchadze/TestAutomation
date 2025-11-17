package core.model.android;

import core.model.android.common.HasWrongAttemptWindow;
import core.model.android.common.WrongAttemptWindow;
import core.utils.hellper.AppiumHelper;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NotFoundException;

@Slf4j
public class OTPPage extends AppiumHelper implements HasWrongAttemptWindow {

    private final String OTP_INPUT = "com.icomvision.bsc.tbc:id/payment_authorization_content";
    private final String RESEND_BTN = "com.icomvision.bsc.tbc:id/flResendWrapper";
    private final String ALTERNATIVE_RESEND_BTN = "com.icomvision.bsc.tbc:id/ivResend";
    private final String SUBMIT_BTN = "com.icomvision.bsc.tbc:id/btTwoFactorConfirm";
    private final String TITLE = "com.icomvision.bsc.tbc:id/tvTitle";
    private final String DESCRIPTION = "com.icomvision.bsc.tbc:id/tvSubtitle";
    private final String WRONG_ATTEMPT_WINDOW_TEXT_VIEW = "android.widget.TextView";
    private final String WRONG_ATTEMPT_WINDOW_BTN = "android:id/button1";
    private final String WRONG_ATTEMPT_WINDOW_TEXT = "ვწუხვართ, მითითებული ინფორმაცია არასწორია, სცადე განმეორებით";

    private final WrongAttemptWindow wrongAttemptWindow = new WrongAttemptWindow();

    public boolean itIsOTPPage() {


        String[] otpPageRequiredElements = new String[]{OTP_INPUT, SUBMIT_BTN, TITLE};
        return itIsCorrectPage(otpPageRequiredElements, "id");
    }


    public void enterOtpCode(String otpCode) {

        try {

            type(OTP_INPUT, otpCode);
            click(SUBMIT_BTN);
        } catch (NotFoundException nfe) {
            log.error("Can't find OTP code element: " + nfe.getMessage());
            throw new RuntimeException("Can't find OTP code element: " + nfe.getMessage());
        }


    }

    public void resendOTPCode() {
        try {
            click(RESEND_BTN);
        } catch (NotFoundException nfe) {
            log.error("Can't found OTP resend button: " + nfe.getMessage());
            throw new RuntimeException("Can't found OTP resend button: " + nfe.getMessage());
        }
    }


    @Override
    public boolean wrongAttemptWindowIsDisplayed(boolean click) {
        return wrongAttemptWindow.wrongAttemptWindowIsDisplayed(click, WRONG_ATTEMPT_WINDOW_BTN);
    }


}
