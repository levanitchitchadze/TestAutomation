package core.base;

import core.enums.PlatformType;
import core.factories.AndroidFactory;
import core.factories.ApiFactory;
import core.model.android.AndroidHomePage;
import core.model.android.AndroidLoginPage;
import core.model.android.AndroidOTPPage;
import core.model.api.ApiHomePage;
import core.model.api.ApiLoginPage;
import core.model.api.ApiOTPPage;
import core.steps.android.AndroidHomeSteps;
import core.steps.android.AndroidLoginSteps;
import core.steps.android.AndroidOTPSteps;
import core.steps.api.ApiHomeSteps;
import core.steps.api.ApiLoginSteps;
import core.steps.api.ApiOTPSteps;
import core.utils.hellper.AppiumHelper;

public class PlatformContextBuilder {

    public static PlatformContext build(String platform) {
        PlatformType type = PlatformType.valueOf(
                platform.toUpperCase()
        );
        PlatformContext ctx = new PlatformContext(type);
        switch (type) {
            case ANDROID -> {

//                try {
                AppiumHelper.androidDriver = new AndroidFactory().getDriver();
                ctx.setAndroidDriver(AppiumHelper.androidDriver);
                ctx.setHomePage(new AndroidHomePage());
                ctx.setHomeSteps(new AndroidHomeSteps());
                ctx.setLoginPage(new AndroidLoginPage());
                ctx.setLoginSteps(new AndroidLoginSteps());
                ctx.setOtpPage(new AndroidOTPPage());
                ctx.setOtpSteps(new AndroidOTPSteps());

                System.out.println("From Context:" + ctx.getLoginSteps());
//                } catch (Exception e) {
//                    throw new RuntimeException("Exception while initialization android context:" + e);
//                }
                System.out.println("Context Type first:" + ctx.getType());


            }
            case API -> {

                ctx.setApiDriver(new ApiFactory().getDriver());
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
