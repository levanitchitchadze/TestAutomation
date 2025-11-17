package core.base;

import core.enums.PlatformType;
import core.factories.AndroidFactory;
import core.factories.ApiFactory;
import core.module.android.AndroidHomePage;
import core.module.android.AndroidLoginPage;
import core.module.android.AndroidOTPPage;
import core.module.api.ApiHomePage;
import core.module.api.ApiLoginPage;
import core.module.api.ApiOTPPage;
import core.steps.android.AndroidHomeSteps;
import core.steps.android.AndroidLoginSteps;
import core.steps.android.AndroidOTPSteps;
import core.steps.api.ApiHomeSteps;
import core.steps.api.ApiLoginSteps;
import core.steps.api.ApiOTPSteps;
import core.utils.api.APIRequestBuilder;
import core.utils.hellper.AppiumHelper;

public class PlatformContextBuilder {

    public static PlatformContext build(String platform) {
        PlatformType type = PlatformType.valueOf(
                platform.toUpperCase()
        );
        PlatformContext ctx = new PlatformContext(type);
        switch (type) {
            case ANDROID -> {

                AppiumHelper.androidDriver = new AndroidFactory().getDriver();
                ctx.setAndroidDriver(AppiumHelper.androidDriver);
                ctx.setHomePage(new AndroidHomePage());
                ctx.setHomeSteps(new AndroidHomeSteps());
                ctx.setLoginPage(new AndroidLoginPage());
                ctx.setLoginSteps(new AndroidLoginSteps());
                ctx.setOtpPage(new AndroidOTPPage());
                ctx.setOtpSteps(new AndroidOTPSteps());


            }
            case API -> {
                APIRequestBuilder.requestSpecification = new ApiFactory().getDriver();
                ctx.setApiDriver(APIRequestBuilder.requestSpecification);
                ctx.setHomePage(new ApiHomePage());
                ctx.setHomeSteps(new ApiHomeSteps());
                ctx.setLoginPage(new ApiLoginPage());
                ctx.setLoginSteps(new ApiLoginSteps());
                ctx.setOtpPage(new ApiOTPPage());
                ctx.setOtpSteps(new ApiOTPSteps());
            }
//            case WEB ->{
//            ctx.setDriver(WebDriverProvider.getDriver());
//                ctx.setHomePage(new WebHomePage());
//                ctx.setHomeSteps(new WebHomeSteps());
//                ctx.setLoginPage(new WebLoginPage());
//                ctx.setLoginSteps(new WebLoginSteps());
//                ctx.setOtpPage(new WebOTPPage());
//                ctx.setOtpSteps(new WebOTPSteps());
//        }
//            case IOS ->{
//            ctx.setDriver(IosDriverProvider.getDriver());
//                ctx.setHomePage(new IosHomePage());
//                ctx.setHomeSteps(new IosHomeSteps());
//                ctx.setLoginPage(new IosLoginPage());
//                ctx.setLoginSteps(new IosLoginSteps());
//                ctx.setOtpPage(new IosOTPPage());
//                ctx.setOtpSteps(new IosOTPSteps());
//        }
            default -> throw new RuntimeException("Can't find correct platform with name: " + platform);
        }


        return ctx;
    }

}
