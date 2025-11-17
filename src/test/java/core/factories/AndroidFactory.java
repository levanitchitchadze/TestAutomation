package core.factories;


import core.base.IPlatformAbstractFactory;
import core.drivers.AndroidDriverProvider;
import core.model.android.AndroidHomePage;
import core.model.android.AndroidLoginPage;
import core.model.android.AndroidOTPPage;
import core.model.common.HomePage;
import core.model.common.LoginPage;
import core.model.common.OTPPage;
import core.steps.android.AndroidHomeSteps;
import core.steps.android.AndroidLoginSteps;
import core.steps.android.AndroidOTPSteps;
import core.steps.common.HomeSteps;
import core.steps.common.LoginSteps;
import core.steps.common.OTPSteps;
import io.appium.java_client.android.AndroidDriver;

public class AndroidFactory implements IPlatformAbstractFactory {

    private final AndroidDriver androidDriver;

    public AndroidFactory() {
        AndroidDriver androidDriver1 = AndroidDriverProvider.getInstance().getDriver();

        this.androidDriver = androidDriver1;
    }

    @Override
    public AndroidDriver getDriver() {

        if (androidDriver == null) {
            throw new RuntimeException("AndroidDriver is null");
        }

        return androidDriver;
    }

    @Override
    public AndroidHomePage createHomePage() {
        return new AndroidHomePage();
    }

    @Override
    public HomeSteps createHomeSteps(HomePage page) {
        return AndroidHomeSteps.getInstance(createHomePage());
    }

    @Override
    public AndroidLoginPage createLoginPage() {
        return new AndroidLoginPage();
    }

    @Override
    public LoginSteps createLoginSteps(LoginPage page) {
        return AndroidLoginSteps.getInstance(createLoginPage());
    }

    @Override
    public AndroidOTPPage createOTPPage() {
        return new AndroidOTPPage();
    }

    @Override
    public OTPSteps createOTPSteps(OTPPage otpPage) {
        return AndroidOTPSteps.getInstance(createOTPPage());
    }


}
