package core.model.api;

import core.model.android.common.HasWrongAttemptWindow;
import core.model.common.OTPPage;
import core.utils.hellper.APIHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiOTPPage extends APIHelper implements HasWrongAttemptWindow, OTPPage {

    @Override
    public boolean wrongAttemptValidation(boolean click) {
        return false;
    }
}
