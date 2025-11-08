package core.factories;

import core.base.IPlatformFactory;
import core.steps.app.LoginSteps;
import core.steps.app.PaymentSteps;

public class IAppFactory implements IPlatformFactory {


    @Override
    public LoginSteps createLoginSteps() {
        return new LoginSteps();
    }


    @Override
    public PaymentSteps createPaymentStep() {
        return new PaymentSteps();
    }

}
