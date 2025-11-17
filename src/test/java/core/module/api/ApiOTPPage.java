package core.module.api;

import core.module.android.common.HasWrongAttemptWindow;
import core.module.common.OTPPage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiOTPPage implements HasWrongAttemptWindow, OTPPage {

    @Override
    public boolean wrongAttemptValidation(boolean click) {
        return false;
    }
}
