package core.module.android;

import core.module.android.common.MenuBar;
import core.module.common.HomePage;
import core.utils.hellper.AppiumHelper;

public class AndroidHomePage extends AppiumHelper implements HomePage {


    private final String ENTER_EASILY_TITLE = "com.icomvision.bsc.tbc:id/tvTitle";
    private final String HEADER_TOOL_BAR = "com.icomvision.bsc.tbc:id/toolbar";
    private final String ADD_PIN_DO_NEVER_ASK_AGAIN_BTN = "com.icomvision.bsc.tbc:id/btDontAskAgain";
    private final String MAKE_DEVICE_TRUSTED_NEVER_ASK_AGAIN_BTN = "com.icomvision.bsc.tbc:id/btDontAskAgain";

    private final String AVAILABLE_AMOUNT_TEXT = "com.icomvision.bsc.tbc:id/cvAmountView";


    private MenuBar menuBar = new MenuBar();

    private String currentPageName = "home";

    public boolean itIsHomePage() {
        String[] loginPageRequiredElements = new String[]{HEADER_TOOL_BAR, AVAILABLE_AMOUNT_TEXT};
        return itIsCorrectPage(loginPageRequiredElements, "id");
    }


    public void navigateTo(String pageName) {
        menuBar.moveTo(pageName, currentPageName);
    }

    public boolean navigateToPagesAndBack(String[] pages) {

        for (String page : pages) {
            boolean move = menuBar.moveTo(page, currentPageName);

            boolean back = menuBar.back();

            if (!move || !back) return false;

        }

        return true;
    }


    public void closeExtraWindows() {
        if (isDisplayed(ADD_PIN_DO_NEVER_ASK_AGAIN_BTN)) click(ADD_PIN_DO_NEVER_ASK_AGAIN_BTN);
        if (isDisplayed(MAKE_DEVICE_TRUSTED_NEVER_ASK_AGAIN_BTN)) click(MAKE_DEVICE_TRUSTED_NEVER_ASK_AGAIN_BTN);
    }


}
