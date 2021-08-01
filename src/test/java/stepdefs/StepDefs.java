package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class StepDefs {

    WebDriver driver;
    String url = "https://parabank.parasoft.com/parabank/index.htm";
    String userName = "john";
    String password = "demo";
    Scenario scenario;

    @Before  // native dependency Injection in cucumber
    public void setUp(Scenario scenario){
        this.scenario = scenario;
    }

    @After
    public void cleanUp(){
        driver.quit();
    }

    @Given("User opened the browser")
    public void user_opened_the_browser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // maximize browser window
        driver.manage().deleteAllCookies(); // delete all cookies
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Given("User clicked on link {string}")
    public void user_clicked_on_link(String linkName) {
        driver.findElement(By.linkText(linkName)).click();
    }


    @Given("User navigated to the application url")
    public void user_navigated_to_the_application_url() {
        driver.get(url);
    }

    @When("User enter username as {string} and password as {string} and click on login button")
    public void user_enter_username_as_and_password_as_and_click_on_login_button(String userName, String password) {
        driver.findElement(By.name("username")).sendKeys(userName);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Log In']")).click();
    }

    @Then("User is able to login in the application")
    public void user_is_able_to_login_in_the_application() {
        Assert.assertEquals("ParaBank | Accounts Overview",driver.getTitle());
        boolean actualTableDisplayed = driver.findElement(By.id("accountTable")).isDisplayed();
        Assert.assertEquals(true,actualTableDisplayed);
    }

    @Given("User is logged in")
    public void user_is_logged_in() { // wrapper step
        user_opened_the_browser();
        user_navigated_to_the_application_url();
        user_enter_username_as_and_password_as_and_click_on_login_button(userName,password);
    }

    @When("User select account as {string} and account number as {string}")
    public void user_select_account_as_and_account_number_as(String type, String accNumber) {
        WebElement dropDownAccType = driver.findElement(By.id("type"));
        Select selectAccType = new Select(dropDownAccType);
        selectAccType.selectByVisibleText(type);

        WebElement dropDownAccNumber = driver.findElement(By.id("fromAccountId"));
        Select selectAccNumber = new Select(dropDownAccNumber);
        selectAccType.selectByVisibleText(accNumber);
    }

    @When("User select account as {string} and any account number")
    public void user_select_account_as_and_account_number_as(String type) {
        WebElement dropDownAccType = driver.findElement(By.id("type"));
        Select selectAccType = new Select(dropDownAccType);
        selectAccType.selectByVisibleText(type);

        WebElement dropDownAccNumber = driver.findElement(By.id("fromAccountId"));
        Select selectAccNumber = new Select(dropDownAccNumber);
        selectAccType.selectByIndex(0);
    }

    @When("User clicks on Button Open New Account")
    public void user_clicks_on_button_open_new_account() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@value ='Open New Account']")).click();

    }

    @Then("Account Opened Message is Displayed")
    public void account_opened_message_is_displayed() {
        WebElement element = driver.findElement(By.xpath("//h1[text()='Account Opened!']"));
        Assert.assertEquals(element.isDisplayed(),true,"Account Success Message");
    }

    @Then("a new account number is generated")
    public void a_new_account_number_is_generated() {
        WebElement element = driver.findElement(By.id("newAccountId"));
        String accountNumber = element.getText();
        Assert.assertEquals(element.isDisplayed(),true,"New Account Number Link");
        scenario.log("New Account number generated as : " + accountNumber);

    }

}



