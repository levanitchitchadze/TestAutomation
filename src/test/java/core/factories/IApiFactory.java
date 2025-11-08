package core.factories;

import core.base.IPlatformFactory;
import core.steps.app.LoginSteps;
import core.steps.app.PaymentSteps;

public class IApiFactory implements IPlatformFactory {
    @Override
    public LoginSteps createLoginSteps() {
        return null;
    }


    @Override
    public PaymentSteps createPaymentStep() {
        return null;
    }
}
