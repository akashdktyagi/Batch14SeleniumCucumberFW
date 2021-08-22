package stepdefs;

import io.cucumber.java.*;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.BaseTest;
import utils.TestContext;

@Log4j2
public class HooksStepDefs extends BaseTest {
    TestContext testContext;

    public HooksStepDefs(TestContext testContext){
        this.testContext = testContext;
    }

    Scenario scenario;


    @Before
    public void setUp(Scenario scenario){
        this.scenario = scenario;
    }

    @After
    public void cleanUp(Scenario scenario){
        if (!(testContext.getDriver()==null)) {
            testContext.getDriver().quit();
        }
    }

    @AfterStep
    public void afterEachStep(){
//        if (scenario.isFailed()) {
        if (!(testContext.getDriver()==null)) {
            TakesScreenshot scrnShot = (TakesScreenshot) testContext.getDriver();
            byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(data, "image/png", "Step Name: " + scenario.getName());
        }
        log.debug("Each step hook is executed, screen shot taken");

    }
}
