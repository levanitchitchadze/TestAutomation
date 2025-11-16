package core.steps.android;

import core.enums.Language;
import core.model.android.LoginPage;
import core.model.android.OTPPage;
import core.steps.common.ILoginSteps;
import core.utils.hellper.AppiumHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginSteps extends AppiumHelper implements ILoginSteps {

    private final LoginPage loginPage = new LoginPage();
    private final OTPPage otpPage = new OTPPage();

    public Boolean notificationsWindowIsVisible(String identifierId) {
        return isDisplayed(identifierId);
    }

    public void notificationPermissions(boolean allow) {
        loginPage.acceptNotifications(allow);
    }


    public boolean wrongAttemptWindowIsDisplayed(boolean click) {
        return loginPage.wrongAttemptWindowIsDisplayed(click);
    }

    public void choseLanguage(Language languageName) {
        loginPage.choseLanguage(languageName);
    }


    public boolean login(String username, String password) {

        boolean performed = loginPage.login(username, password);
//        return true;
        return performed && checkLogin();

    }


    public boolean checkLogin() {
        return otpPage.itIsOTPPage();
    }
}
