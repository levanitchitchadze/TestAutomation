package core.base;

import core.module.common.HomePage;
import core.module.common.LoginPage;
import core.module.common.OTPPage;
import core.steps.common.HomeSteps;
import core.steps.common.LoginSteps;
import core.steps.common.OTPSteps;

public interface IPlatformAbstractFactory {

    Object getDriver();


    // Home Family
    HomePage createHomePage();

    HomeSteps createHomeSteps(HomePage page);


    // Login Family
    LoginPage createLoginPage();

    LoginSteps createLoginSteps(LoginPage page);


    // OTP Family
    OTPPage createOTPPage();

    OTPSteps createOTPSteps(OTPPage otpPage);


}
