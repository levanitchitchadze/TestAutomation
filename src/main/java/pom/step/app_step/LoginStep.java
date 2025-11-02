package pom.step.app_step;

import pom.data.test_object.app_data.Language;
import pom.module.app_page.LoginPage;
import pom.util.hellper.AppiumHelper;

public class LoginStep extends AppiumHelper {

    private LoginPage loginPage;

    public LoginStep(LoginPage loginPage) {
        this.loginPage = loginPage;
    }


    public Boolean notificationsWindowIsVisible(String identifierId) {
        return isDisplayed(identifierId);
    }

    public void notificationPermissions(boolean allow) {
        LoginPage lp = loginPage;

        if (!isDisplayed(lp.getNOTIFICATION_DENY_BTN(), 1)) {
            return;
        }
        if (allow) {
            click(lp.getNOTIFICATION_ALLOW_BTN());
        } else {
            click(lp.getNOTIFICATION_DENY_BTN());
        }

    }


    public void choseLanguage(Language languageName) {
        LoginPage lp = loginPage;
        if (!isDisplayed(lp.getLANGUAGE_OPTION_GEO(), 1)) {
            return;
        }
        if (languageName == Language.GEO) {
            click(lp.getLANGUAGE_OPTION_GEO());
        } else if (languageName == Language.ENG) {
            click(lp.getLANGUAGE_OPTION_ENG());
        }
    }


    public boolean login(String username, String password) {
        LoginPage lp = loginPage;
        waitTimeOut(3);
        type(lp.getUSERNAME_INPUT(), username);
        type(lp.getPASSWORD_INPUT(), password);
        click(lp.getSUBMIT_BTN());

        return checkLogin();

    }


    public boolean checkLogin() {
        String wrongAttemptWindow = loginPage.getWRONG_ATTEMPT_WINDOW();
        if (isDisplayed(wrongAttemptWindow, 2)) {
            click(wrongAttemptWindow);
            return false;
        }
        return isNotDisplayed(loginPage.getWELCOME_TEXT());
    }
}
