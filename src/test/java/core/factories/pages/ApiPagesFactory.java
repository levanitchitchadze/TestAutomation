package core.factories.pages;

import core.base.IPlatformAbstractFactory;
import core.steps.android.LoginSteps;
import core.steps.android.PaymentSteps;

public class ApiPagesFactory implements IPlatformAbstractFactory {
    @Override
    public LoginSteps createLoginSteps() {
        return null;
    }


    @Override
    public PaymentSteps createPaymentStep() {
        return null;
    }
}
