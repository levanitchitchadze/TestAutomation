package core.factories.steps;

import core.base.IPlatformAbstractFactory;
import core.steps.android.LoginSteps;
import core.steps.android.PaymentSteps;

public class AndroidStepsFactory implements IPlatformAbstractFactory {


    @Override
    public LoginSteps createLoginSteps() {
        return new LoginSteps();
    }


    @Override
    public PaymentSteps createPaymentStep() {
        return new PaymentSteps();
    }

}
