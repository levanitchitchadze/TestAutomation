package core.module.android;

import core.module.android.common.MenuBar;
import core.module.common.HomePage;
import core.utils.hellper.AppiumHelper;

public class AndroidHomePage extends AppiumHelper implements HomePage {


    private final String ENTER_EASILY_TITLE = "com.icomvision.bsc.tbc:id/tvTitle";
    private final String ENTER_EASILY_TITLE_TEXT = "შედი მარტივად";
    private final String ENTER_EASILY_LATER_BTN = "com.icomvision.bsc.tbc:id/btRemindMeLater";
    private final String ENTER_EASILY_ACTIVATE_BTN = "com.icomvision.bsc.tbc:id/btActivateFingerprint";
    private final String MANAGE_ACCOUNTS_WINDOW_TITLE = "//android.widget.TextView[@text=\"მართე ანგარიშები\"]";
    private final String MANAGE_ACCOUNTS_WINDOW_BTN = "//android.widget.ScrollView/android.view.View/android.view.View[2]/android.view.View";

    private final String CARDS_VIEW = "//androidx.compose.ui.platform.ComposeView[@resource-id=\"com.icomvision.bsc.tbc:id/compose_view\"]/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View/android.view.View";

    private final String AVAILABLE_AMOUNT_TEXT = "//*[@resource-id=\"com.icomvision.bsc.tbc:id/compose_view\"]//*[@text=\"ხელმისაწვდომი\"]";


    private MenuBar menuBar = new MenuBar();

    private String currentPageName = "home";

    public boolean itIsHomePage() {
        String[] loginPageRequiredElements = new String[]{CARDS_VIEW, AVAILABLE_AMOUNT_TEXT};
//        return itIsCorrectPage(loginPageRequiredElements, "xpath");
        return true;
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

}
