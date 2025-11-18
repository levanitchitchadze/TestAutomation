package core.steps.common;

import core.enums.Language;

public interface LoginSteps {
    boolean login(String username, String password);

    boolean login();

    boolean fastLogin(String password);

    void itIsLoginPage();

    void initCredentials(String usernameType, String passwordType);

    void notificationPermissions(boolean allow);

    void choseLanguage(Language languageName);

    void wrongAttemptWindowValidation(boolean click, boolean shouldBeDisplayed);

}
