package core.steps.common;

public interface OTPSteps {

    String getLatestOTPCode();

    void itIsOTPPage();

    void resendOTPCode();

    void checkNewOTPCode();

    void enterOTP(String otp);

    void wrongAttemptWindowValidation(boolean click);

    void wrongAttemptWindowValidation(boolean click, boolean shouldBeDisplayed);
}
