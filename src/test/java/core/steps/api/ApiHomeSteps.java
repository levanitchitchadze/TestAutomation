package core.steps.api;

import core.steps.common.HomeSteps;
import core.utils.hellper.AppiumHelper;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApiHomeSteps extends AppiumHelper implements HomeSteps {


    @Override
    public boolean itIsHomePage() {
        return false;
    }

    @Override
    public void navigateToAllPage() {

    }
}
