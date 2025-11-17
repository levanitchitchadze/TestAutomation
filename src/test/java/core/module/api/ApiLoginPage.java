package core.module.api;


import core.enums.Language;
import core.module.android.common.HasWrongAttemptWindow;
import core.module.common.LoginPage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

// There we have selectors for login page
@Getter
@Slf4j
public class ApiLoginPage implements HasWrongAttemptWindow, LoginPage {

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
