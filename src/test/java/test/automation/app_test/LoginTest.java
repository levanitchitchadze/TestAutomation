package automation.app_test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pom.data.test_object.app_data.Language;
import pom.data.test_object.app_data.LoginData;
import pom.module.app_page.LoginPage;
import pom.step.app_step.LoginStep;

import java.util.Scanner;

//    There are login tests I want to run, each method has a meaningful name, so just read :d
public class LoginTest {

    private LoginPage loginPage;
    private LoginStep loginSteps;
    private LoginData loginData;

    @BeforeClass
    void setUp() {
        System.out.println("Mobile APP Test");

        Scanner scanner = new Scanner(System.in);
        loginPage = new LoginPage();
        loginData = new LoginData();
        loginSteps = new LoginStep(loginPage);


        System.out.println("Please enter TBC mobile bank");
        System.out.println("username:");
        String username = scanner.nextLine();

        System.out.println("password:");
        String password = scanner.nextLine();


    }

    @Test
    void loginWithIncorrectUsername() {

    }

    @Test
    void loginWithIncorrectPassword() {

    }

    @Test
    void loginSuccessfully() {
        loginSteps.notificationPermissions(true);
        loginSteps.choseLanguage(Language.GEO);
        loginSteps.login(loginData.getUsername(), loginData.getPassword());
    }


}
