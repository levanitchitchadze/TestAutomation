package core.steps.android;

import core.data.LoginData;
import core.enums.Language;
import core.module.android.AndroidLoginPage;
import core.module.android.AndroidOTPPage;
import core.steps.common.LoginSteps;
import core.utils.hellper.AppiumHelper;
import core.utils.messages.output.error.TestFailMessages;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class AndroidLoginSteps extends AppiumHelper implements LoginSteps {

    private final AndroidLoginPage loginPage = new AndroidLoginPage();
    private final AndroidOTPPage otpPage = new AndroidOTPPage();
    private final LoginData loginData = new LoginData();

    @Setter
    private String username;
    @Setter
    private String password;


    public static LoginSteps getInstance(AndroidLoginPage loginPage) {
        return new AndroidLoginSteps();
    }


    public void itIsLoginPage() {

        assert loginPage.itIsLoginPage() : TestFailMessages.ITS_NOT_CORRECT_PAGE;

    }


    public void initCredentials(String usernameType, String passwordType) {

        if (usernameType.equals("correct")) {
            username = loginData.getUsername();
        } else {
            username = loginData.getIncorrectUsername();
        }


        if (passwordType.equals("correct")) {
            password = loginData.getPassword();
        } else {
            password = loginData.getIncorrectPassword();
        }
    }


    public void notificationPermissions(boolean allow) {
        loginPage.acceptNotifications(allow);
    }


    public void choseLanguage(Language languageName) {
        loginPage.choseLanguage(languageName);
    }

    @Override
    public boolean login(String username, String password) {
        boolean performed = false;
        if (loginPage.itIsFastLoginPage()) performed = fastLogin(password);
        else performed = loginPage.login(username, password);
        return performed && checkLogin();
    }

    public boolean login() {


        boolean performed = false;
        if (loginPage.itIsFastLoginPage()) performed = fastLogin(password);

        else performed = loginPage.login(username, password);
        return performed && checkLogin();

    }

    @Override
    public boolean fastLogin(String password) {
        return false;
    }
    //


    public boolean fastLogin() {

        return loginPage.fastLogin(password);
    }

    public void wrongAttemptWindowValidation(boolean click) {
        assert !loginPage.wrongAttemptValidation(click) : TestFailMessages.WRONG_ATTEMPT_WINDOW_DISPLAYED;
    }

    public void wrongAttemptWindowValidation(boolean click, boolean shouldBeDisplayed) {

        assert !(loginPage.wrongAttemptValidation(click) ^ shouldBeDisplayed) : TestFailMessages.WRONG_ATTEMPT_WINDOW_DISPLAYED;


    }

    public boolean checkLogin() {
        return otpPage.itIsOTPPage();
    }
}


