package core.factories.pages;

public class PageFactory {

    public static ApiPagesFactory getApiFactory() {
        return new ApiPagesFactory();
    }

    public static HomePagesFactory getAndroidFactory() {
//        return new HomePagesFactory();
        return null;
    }

    public static IosPagesFactory getIosFactory() {
        return new IosPagesFactory();
    }

    public static WebPagesFactory getWebFactory() {
        return new WebPagesFactory();
    }

}
