package core.model.app;


import lombok.Getter;

// There we have selectors for login page
@Getter
public class LoginPage {

    //    All the constants are screaming :D.
    private final String NOTIFICATION_ALLOW_BTN = "com.android.permissioncontroller:id/permission_allow_button";
    private final String NOTIFICATION_DENY_BTN = "com.android.permissioncontroller:id/permission_allow_button";
    private final String LANGUAGE_OPTION_GEO = "android:id/button1";
    private final String LANGUAGE_OPTION_ENG = "android:id/button2";
    private final String WELCOME_TEXT = "com.icomvision.bsc.tbc:id/tvWelcome";
    private final String WRONG_ATTEMPT_WINDOW = "android:id/button1";
    private final String USERNAME_INPUT = "com.icomvision.bsc.tbc:id/edUsername";
    private final String PASSWORD_INPUT = "com.icomvision.bsc.tbc:id/edPassword";
    private final String SUBMIT_BTN = "com.icomvision.bsc.tbc:id/btLogin";

}
