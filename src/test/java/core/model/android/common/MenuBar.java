package core.model.android.common;

import core.utils.hellper.AppiumHelper;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
public class MenuBar extends AppiumHelper {
    private final String MENU_BAR_OPTION_HOME = "com.icomvision.bsc.tbc:id/mnuHome";
    private final String MENU_BAR_OPTION_PRODUCTS = "com.icomvision.bsc.tbc:id/mnuProducts";
    private final String MENU_BAR_OPTION_TRANSFERS = "com.icomvision.bsc.tbc:id/mnuPaymentsTransfers";
    private final String MENU_BAR_OPTION_PAY_BILL = "com.icomvision.bsc.tbc:id/mnuPayBill";
    private final String MENU_BAR_OPTION_PAY_MOBILE = "com.icomvision.bsc.tbc:id/mnuTopUp";
    private List<String> pagesHistory = new ArrayList<>();

    private HashMap<String, String> pageSelectors = new HashMap<>();

    
    private void fillDict() {
        pageSelectors.put("home", MENU_BAR_OPTION_HOME);
        pageSelectors.put("products", MENU_BAR_OPTION_PRODUCTS);
        pageSelectors.put("transfers", MENU_BAR_OPTION_TRANSFERS);
        pageSelectors.put("bills", MENU_BAR_OPTION_PAY_BILL);
        pageSelectors.put("mobile", MENU_BAR_OPTION_PAY_MOBILE);
    }


    private void changePage(String pageName) {
        if (pageSelectors == null) fillDict();
        try {
            click(pageSelectors.get(pageName.toLowerCase()));

        } catch (NotFoundException nfe) {
            throw new RuntimeException("Cant find menu option please check one of them:home,products,transfers,bills" + nfe);
        }

    }

    public boolean back() {
        try {
            changePage(pagesHistory.getLast());
            pagesHistory.removeLast();
            return true;
        } catch (NoSuchElementException nsee) {
            log.error("There is not old page to move back " + nsee);
            return false;
        }


    }

    public boolean moveTo(String pageName, String pageFrom) {
        try {
            pagesHistory.add(pageFrom.toLowerCase());
            changePage(pageName);
            return true;

        } catch (NotFoundException notFoundException) {
            log.error("Not found menu item " + notFoundException);
            return false;

        }

    }


}
