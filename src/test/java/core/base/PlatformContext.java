package core.base;

import core.enums.PlatformType;
import core.module.common.HomePage;
import core.module.common.LoginPage;
import core.module.common.MobileTopUpPage;
import core.module.common.OTPPage;
import core.steps.common.HomeSteps;
import core.steps.common.LoginSteps;
import core.steps.common.MobileTopUpSteps;
import core.steps.common.OTPSteps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class PlatformContext {

    @Setter(AccessLevel.PRIVATE)
    private PlatformType type;


    private AndroidDriver androidDriver;
    private IOSDriver iosDriver;
    private RequestSpecification apiDriver;
    private WebDriver webDriver;

    private HomeSteps homeSteps;
    private LoginSteps loginSteps;
    private OTPSteps otpSteps;
    private MobileTopUpSteps mobileTopUp;

    private HomePage homePage;
    private LoginPage loginPage;
    private OTPPage otpPage;
    private MobileTopUpPage mobileTopUpPage;


    public PlatformContext(PlatformType type) {
        this.type = type;
    }


}
