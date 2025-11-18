package test.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


// Cucumber runner class. 
// There are pathes where my step definitions and cucumber configs are saved 
@CucumberOptions(
        features = "src/test/java/test/automation/feature",
        glue = {
                "test/automation/stepDefinition",
                "core/config/cucumber"
        },
        plugin = {
                "pretty",
                "html:cucumber-reports/cucumber-pretty.html",
                "json:cucumber-reports/CucumberTestReport.json"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
