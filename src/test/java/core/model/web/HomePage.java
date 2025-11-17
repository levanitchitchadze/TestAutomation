package core.model.web;

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
