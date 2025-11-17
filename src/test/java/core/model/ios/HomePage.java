package core.model.ios;

import core.factories.pages.HomePagesFactory;

public class HomePage implements HomePagesFactory {
    @Override
    public boolean navigateToPagesAndBack(String[] pages) {
        return false;
    }

    @Override
    public void navigateTo(String pageName) {

    }
}
