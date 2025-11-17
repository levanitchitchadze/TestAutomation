package core.factories.pages;

import core.base.IPlatformAbstractFactory;
import core.drivers.IDriverProvider;
import core.steps.android.LoginSteps;
import core.steps.android.PaymentSteps;

public class WebPagesFactory implements IPlatformAbstractFactory {
    @Override
    public IDriverProvider getDriverProvider() {
        return null;
    }

    @Override
    public LoginSteps createLoginSteps() {
        return null;
    }

    @Override
    public PaymentSteps createPaymentStep() {
        return null;
    }
}
