package core.model.api;


import core.enums.Language;
import core.model.android.common.HasWrongAttemptWindow;
import core.model.common.LoginPage;
import core.utils.hellper.APIHelper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

// There we have selectors for login page
@Getter
@Slf4j
public class ApiLoginPage extends APIHelper implements HasWrongAttemptWindow, LoginPage {

    @Override
    public boolean wrongAttemptValidation(boolean click) {
        return false;
    }

    @Override
    public boolean itIsLoginPage() {
        return false;
    }

    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public void choseLanguage(Language language) {

    }
}
