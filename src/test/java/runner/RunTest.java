package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "classpath:features/Login.feature",
        glue = "stepdefs",
        plugin = {"pretty",
                "html:target/cucumber-reports.html"
        },
        dryRun = false
)
public class RunTest  extends AbstractTestNGCucumberTests {
}
