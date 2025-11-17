package core.module.android;

import core.module.common.MobileTopUpPage;
import core.utils.hellper.AppiumHelper;

public class AndroidMobileTopUpPage extends AppiumHelper implements MobileTopUpPage {


    private final String MOBILE_NUMBER_ADD_BUTTON = "";

    public boolean itIsMobileTopUpPage() {
//        String[] loginPageRequiredElements = new String[]{CARDS_VIEW, AVAILABLE_AMOUNT_TEXT};
//        return itIsCorrectPage(loginPageRequiredElements, "xpath");
        return false;
    }

}
