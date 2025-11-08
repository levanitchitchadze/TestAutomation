package core.steps.android;

import core.enums.Language;
import core.model.android.LoginPage;
import core.steps.common.ILoginSteps;
import core.utils.hellper.AppiumHelper;

public class LoginSteps extends AppiumHelper implements ILoginSteps {

    private LoginPage loginPage = new LoginPage();


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
