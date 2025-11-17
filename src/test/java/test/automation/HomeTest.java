package test.automation;

import core.base.TestBase;
import core.data.HomeData;
import core.steps.common.HomeSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomeTest extends TestBase {


    HomeSteps homeSteps;
    HomeData homeData;

    @BeforeClass
    void setUpHomeTest() {
        homeData = new HomeData();
        homeSteps = ctx.getHomeSteps();
    }


    @Test(dependsOnGroups = {"otpPositive"})
    void moveToMenuBarPages() {

        System.out.println("Context in test Method:" + ctx);
        homeSteps.itIsHomePage();
        homeSteps.navigateToAllPage();
    }


}
