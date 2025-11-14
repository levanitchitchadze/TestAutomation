package core.model.android;


import core.enums.Language;
import core.utils.hellper.AppiumHelper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NotFoundException;

// There we have selectors for login page
@Getter
@Slf4j
public class LoginPage extends AppiumHelper {

    //    All the constants are screaming :D.
    private final String NOTIFICATION_ALLOW_BTN = "com.android.permissioncontroller:id/permission_allow_button";
    private final String NOTIFICATION_DENY_BTN = "com.android.permissioncontroller:id/permission_allow_button";
    private final String LANGUAGE_OPTION_GEO = "android:id/button1";
    private final String LANGUAGE_OPTION_ENG = "android:id/button2";
    private final String WELCOME_TEXT = "com.icomvision.bsc.tbc:id/tvWelcome";
    private final String WRONG_ATTEMPT_WINDOW_BTN = "android:id/button1"; // android:id/button1
    private final String USERNAME_INPUT = "com.icomvision.bsc.tbc:id/edUsername";
    private final String PASSWORD_INPUT = "com.icomvision.bsc.tbc:id/edPassword";
    private final String SUBMIT_BTN = "com.icomvision.bsc.tbc:id/btLogin";

    public boolean itIsLoginPage() {
        return isDisplayed(USERNAME_INPUT) && isDisplayed(PASSWORD_INPUT) && isDisplayed(SUBMIT_BTN);
    }


    public void acceptNotifications(boolean allow) {
        if (isNotDisplayed(NOTIFICATION_DENY_BTN, 3)) return;

        if (allow) click(NOTIFICATION_ALLOW_BTN);
        else click(NOTIFICATION_DENY_BTN);

    }

    public boolean isWrongAttemptWindowDisplayed(boolean click) {

        waitTimeOut(10);
        if (isDisplayed(WRONG_ATTEMPT_WINDOW_BTN)) {
            if (click) click(WRONG_ATTEMPT_WINDOW_BTN);
            return true;
        }
        return isNotDisplayed(getWELCOME_TEXT());
    }

    public boolean login(String username, String password) {
        waitTimeOut(3);
        try {
//            waitFor(SUBMIT_BTN);
            type(USERNAME_INPUT, username);
            type(PASSWORD_INPUT, password);
            click(SUBMIT_BTN);
            return true;
        } catch (NotFoundException nfe) {
            log.error("Can't find login element: " + nfe.getMessage());
            return false;
        }

    }

    public void choseLanguage(Language languageName) {


        if (!isDisplayed(LANGUAGE_OPTION_GEO, 1)) {
            return;
        }
        if (languageName == Language.GEO) {
            click(LANGUAGE_OPTION_GEO);
        } else if (languageName == Language.ENG) {
            click(LANGUAGE_OPTION_ENG);
        }
    }
}
