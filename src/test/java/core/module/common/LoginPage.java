package core.module.common;

import core.enums.Language;

public interface LoginPage {
    boolean itIsLoginPage();

    boolean login(String username, String password);

    void choseLanguage(Language language);
}
