package core.factories.pages;

public interface HomePagesFactory {


    boolean navigateToPagesAndBack(String[] pages);

    void navigateTo(String pageName);

}
