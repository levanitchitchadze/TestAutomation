package core.steps.android;

import core.data.MenuBarData;
import core.module.android.AndroidHomePage;
import core.module.common.HomePage;
import core.steps.common.HomeSteps;
import core.utils.hellper.AppiumHelper;
import core.utils.messages.output.error.TestFailMessages;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AndroidHomeSteps extends AppiumHelper implements HomeSteps {

    private AndroidHomePage homePage = new AndroidHomePage();
    private MenuBarData menuBarData = new MenuBarData();


    public static HomeSteps getInstance(HomePage homePage) {
        return new AndroidHomeSteps();
    }

    public void navigateTo(String pageName) {
        homePage.navigateTo(pageName);

    }

    @Given("Home page is open")
    public boolean itIsHomePage() {

        return homePage.itIsHomePage();
    }

    @When("User clicks menu bar element")
    @Then("It changes page")
    public void navigateToAllPage() {
        assert itIsHomePage() : TestFailMessages.ITS_NOT_CORRECT_PAGE;

        String[] pageOptions = menuBarData.getBOTTOM_MENU_PAGE_OPTIONS();
        assert homePage.navigateToPagesAndBack(pageOptions) : TestFailMessages.CANT_MOVE_MENU_PAGE;

    }

}
