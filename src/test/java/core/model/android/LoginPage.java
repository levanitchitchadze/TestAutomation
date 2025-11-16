package core.model.android;


import core.enums.Language;
import core.model.android.common.HasWrongAttemptWindow;
import core.model.android.common.WrongAttemptWindow;
import core.utils.hellper.AppiumHelper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NotFoundException;

// There we have selectors for login page
@Getter
@Slf4j
public class LoginPage extends AppiumHelper implements HasWrongAttemptWindow {

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


    private final WrongAttemptWindow wrongAttemptWindow = new WrongAttemptWindow();

    public boolean itIsLoginPage() {
        String[] loginPageRequiredElements = new String[]{USERNAME_INPUT, PASSWORD_INPUT, SUBMIT_BTN};
        return itIsCorrectPage(loginPageRequiredElements);
    }


    public void acceptNotifications(boolean allow) {
        if (isNotDisplayed(NOTIFICATION_DENY_BTN, 3)) return;

        if (allow) click(NOTIFICATION_ALLOW_BTN);
        else click(NOTIFICATION_DENY_BTN);

    }

    @Override
    public boolean wrongAttemptWindowIsDisplayed(boolean click) {

        return wrongAttemptWindow.wrongAttemptWindowIsDisplayed(click, WRONG_ATTEMPT_WINDOW_BTN);

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
            throw new RuntimeException("Can't find login elements:" + nfe);
//            return false;
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
