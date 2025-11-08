package core.factories.pages;

public class PageFactory {

    public static ApiPagesFactory getApiFactory() {
        return new ApiPagesFactory();
    }

    public static AndroidPagesFactory getAndroidFactory() {
        return new AndroidPagesFactory();
    }

    public static IosPagesFactory getIosFactory() {
        return new IosPagesFactory();
    }

    public static WebPagesFactory getWebFactory() {
        return new WebPagesFactory();
    }

}
