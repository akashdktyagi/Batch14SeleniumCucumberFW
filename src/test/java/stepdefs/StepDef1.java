package stepdefs;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bson.types.CodeWScope;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.BaseTest;
import utils.TestContext;

public class StepDef1 extends BaseTest{

    TestContext testContext;

    public StepDef1(TestContext testContext){
        this.testContext = testContext;
    }

    Scenario scenario;
    @Before
    public void setUp(Scenario scenario){
        this.scenario = scenario;
    }

    @When("User select account as {string} and any account number")
    public void user_select_account_as_and_account_number_as(String type) {
        WebElement dropDownAccType = testContext.getDriver().findElement(By.id("type"));
        Select selectAccType = new Select(dropDownAccType);
        selectAccType.selectByVisibleText(type);

        WebElement dropDownAccNumber = testContext.getDriver().findElement(By.id("fromAccountId"));
        Select selectAccNumber = new Select(dropDownAccNumber);
        selectAccType.selectByIndex(0);
    }

    @When("User clicks on Button Open New Account")
    public void user_clicks_on_button_open_new_account() throws InterruptedException {
        Thread.sleep(5000);
        testContext.getDriver().findElement(By.xpath("//input[@value ='Open New Account']")).click();

    }

    @Then("Account Opened Message is Displayed")
    public void account_opened_message_is_displayed() {
        WebElement element = testContext.getDriver().findElement(By.xpath("//h1[text()='Account Opened!']"));
        Assert.assertEquals(element.isDisplayed(),true,"Account Success Message");
    }

    @Then("a new account number is generated")
    public void a_new_account_number_is_generated() {
        WebElement element = testContext.getDriver().findElement(By.id("newAccountId"));
        String accountNumber = element.getText();
        Assert.assertEquals(element.isDisplayed(),true,"New Account Number Link");
        scenario.log("New Account number generated as : " + accountNumber);

    }

}
