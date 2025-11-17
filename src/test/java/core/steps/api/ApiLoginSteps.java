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
    public boolean fastLogin(String password) {
        return false;
    }

    @Override
    public void choseLanguage(Language languageName) {

    }
}


