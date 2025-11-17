package core.factories.steps;

public class StepsFactory {


    public static ApiStepsFactory getApiFactory() {
        return new ApiStepsFactory();
    }

    public static HomeStepsFactory getAndroidFactory() {
        return new HomeStepsFactory();
    }

    public static IosStepsFactory getIosFactory() {
        return new IosStepsFactory();
    }

    public static WebStepsFactory getWebFactory() {
        return new WebStepsFactory();
    }
}
