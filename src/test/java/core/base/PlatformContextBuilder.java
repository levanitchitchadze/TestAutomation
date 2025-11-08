package core.base;

import core.drivers.AndroidDriverProvider;
import core.drivers.IosDriverProvider;
import core.drivers.WebDriverProvider;
import core.enums.PlatformType;
import org.testng.annotations.Parameters;

public class PlatformContextBuilder {

    @Parameters({"platform"})
    public static PlatformContext build(String platform) {
        PlatformType type = PlatformType.valueOf(
                platform.toUpperCase()
        );

        PlatformContext ctx = new PlatformContext(type);

        switch (type) {
            case WEB -> ctx.setWebDriver(WebDriverProvider.create());
            case ANDROID -> ctx.setWebDriver(AndroidDriverProvider.create());
            case IOS -> ctx.setWebDriver(IosDriverProvider.create());
        }

        return ctx;
    }

}
