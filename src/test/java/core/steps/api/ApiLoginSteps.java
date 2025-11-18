package core.steps.api;

import core.enums.Language;
import core.steps.common.LoginSteps;
import core.utils.hellper.AppiumHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiLoginSteps extends AppiumHelper implements LoginSteps {

    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public boolean login() {
        return false;
    }

    @Override
    public boolean fastLogin(String password) {
        return false;
    }

    @Override
    public void itIsLoginPage() {

    }

    @Override
    public void initCredentials(String usernameType, String passwordType) {

    }

    @Override
    public void notificationPermissions(boolean allow) {

    }

    @Override
    public void choseLanguage(Language languageName) {

    }

    @Override
    public void wrongAttemptWindowValidation(boolean click, boolean shouldDisplayed) {

    }
}


