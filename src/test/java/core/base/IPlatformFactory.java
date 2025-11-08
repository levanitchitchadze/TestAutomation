package core.base;

import core.steps.app.LoginSteps;
import core.steps.app.PaymentSteps;

public interface IPlatformFactory {

    LoginSteps createLoginSteps();


    PaymentSteps createPaymentStep();

}
