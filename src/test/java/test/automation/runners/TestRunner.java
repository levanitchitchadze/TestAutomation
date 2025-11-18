package test.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/test/automation/feature",
        glue = {
                "test/automation/stepDefinition",
        },
        plugin = {
                "pretty",
                "html:cucumber-reports/cucumber-pretty.html",
                "json:cucumber-reports/CucumberTestReport.json"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
