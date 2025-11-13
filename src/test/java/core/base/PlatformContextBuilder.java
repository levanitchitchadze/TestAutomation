package core.base;

import core.drivers.AndroidDriverProvider;
import core.drivers.ApiClientProvider;
import core.drivers.IosDriverProvider;
import core.drivers.WebDriverProvider;
import core.enums.PlatformType;

public class PlatformContextBuilder {

    public static PlatformContext build(String platform) {
        PlatformType type = PlatformType.valueOf(
                platform.toUpperCase()
        );
        PlatformContext ctx = new PlatformContext(type);

        switch (type) {
            case WEB -> ctx.setWebDriver(WebDriverProvider.getDriver());
            case ANDROID -> ctx.setAndroidDriver(AndroidDriverProvider.getDriver());
            case IOS -> ctx.setIosDriver(IosDriverProvider.getDriver());
            case API -> ctx.setApiClient(ApiClientProvider.getClient());
        }

        return ctx;
    }

}
