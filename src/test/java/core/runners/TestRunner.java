package core.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"test.automation", "core.steps.android", "core.steps.ios", "core.steps.api", "core.steps.web", "core.drivers", "core.config.cucumber"},
        plugin = {
                "pretty",
                "html:cucumber-reports/cucumber-pretty.html",
                "json:cucumber-reports/CucumberTestReport.json"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
