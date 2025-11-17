package core.factories;

import core.base.IPlatformAbstractFactory;
import core.drivers.ApiClientProvider;
import core.model.common.HomePage;
import core.model.common.LoginPage;
import core.model.common.OTPPage;
import core.steps.common.HomeSteps;
import core.steps.common.LoginSteps;
import core.steps.common.OTPSteps;
import io.restassured.specification.RequestSpecification;

public class ApiFactory implements IPlatformAbstractFactory {
    private final RequestSpecification client;


    public ApiFactory() {
        this.client = (RequestSpecification) new ApiClientProvider().getDriver();
    }

    @Override
    public RequestSpecification getDriver() {
        return client;
    }


    @Override
    public HomePage createHomePage() {
        return null;
    }

    @Override
    public HomeSteps createHomeSteps(HomePage page) {
        return null;
    }

    @Override
    public LoginPage createLoginPage() {
        return null;
    }

    @Override
    public LoginSteps createLoginSteps(LoginPage page) {
        return null;
    }

    @Override
    public OTPPage createOTPPage() {
        return null;
    }

    @Override
    public OTPSteps createOTPSteps(OTPPage otpPage) {
        return null;
    }


}
