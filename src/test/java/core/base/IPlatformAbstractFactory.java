package core.base;

import core.steps.android.LoginSteps;
import core.steps.android.PaymentSteps;

public interface IPlatformAbstractFactory {

    LoginSteps createLoginSteps();

    PaymentSteps createPaymentStep();

}
