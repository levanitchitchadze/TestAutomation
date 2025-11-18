package core.steps.api;

import core.steps.common.OTPSteps;
import core.utils.hellper.AppiumHelper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class ApiOTPSteps extends AppiumHelper implements OTPSteps {


    @Override
    public String getLatestOTPCode() {
        return "";
    }

    @Override
    public void itIsOTPPage() {

    }

    @Override
    public void resendOTPCode() {

    }

    @Override
    public void checkNewOTPCode() {

    }

    @Override
    public void enterOTP(String otp) {

    }

    @Override
    public void wrongAttemptWindowValidation(boolean click) {

    }

    @Override
    public void wrongAttemptWindowValidation(boolean click, boolean shouldBeDisplayed) {

    }
}
