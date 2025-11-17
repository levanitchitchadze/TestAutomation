package core.base;

import core.drivers.IDriverProvider;
import core.factories.pages.HomePagesFactory;
import core.steps.android.PaymentSteps;

public interface IPlatformAbstractFactory {

    IDriverProvider getDriverProvider();

    HomePagesFactory createHomeLoginSteps();

    PaymentSteps createPaymentStep();


}
