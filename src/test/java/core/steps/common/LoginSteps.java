package core.steps.common;

import core.enums.Language;

public interface LoginSteps {
    boolean login(String username, String password);

    boolean fastLogin(String password);

    void choseLanguage(Language languageName);
}
