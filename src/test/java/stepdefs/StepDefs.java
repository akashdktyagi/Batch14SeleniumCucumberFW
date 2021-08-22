package stepdefs;

import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.BaseTest;
import utils.DriverFactory;
import utils.TestContext;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Log4j2
public class StepDefs extends BaseTest {

    TestContext testContext;

    public StepDefs(TestContext testContext){
        this.testContext = testContext;
    }


    Scenario scenario;

    @Before  // native dependency Injection in cucumber
    public void setUp(Scenario scenario){
        this.scenario = scenario;
    }

    @Given("User opened the browser")
    public void user_opened_the_browser() {
        String browserName = System.getProperty("browser");
        testContext.setDriver(DriverFactory.createInstance(browserName));
        log.debug("Chrime Intialized");
        testContext.getDriver().manage().window().maximize(); // maximize browser window
        log.debug("Browser Maximized");
        testContext.getDriver().manage().deleteAllCookies(); // delete all cookies
        testContext.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        log.debug("Each step hook is executed, screen shot taken");
    }

    @Given("User clicked on link {string}")
    public void user_clicked_on_link(String linkName) {
        testContext.getDriver().findElement(By.linkText(linkName)).click();
        log.debug("Link Clicked. Link Name" + linkName);
    }


    @Given("User navigated to the application url")
    public void user_navigated_to_the_application_url() {
        testContext.getDriver().get(url);
        log.debug("URL navigated: " + url);
    }

    @When("User enter username as {string} and password as {string} and click on login button")
    public void user_enter_username_as_and_password_as_and_click_on_login_button(String userName, String password) {
        testContext.getDriver().findElement(By.name("username")).sendKeys(userName);
        log.debug("Username enterd: " + userName);
        testContext.getDriver().findElement(By.name("password")).sendKeys(password);
        log.debug("password enterd: " + password);
        testContext.getDriver().findElement(By.xpath("//input[@value='Log In']")).click();
        log.debug("Login button Click");
    }

    @When("User enter username and password as in below table and click on login button")
    public void user_enter_username_and_password_as_in_below_table_and_click_on_login_button(Map<String,String> userCred) {
        testContext.getDriver().findElement(By.name("username")).sendKeys(userCred.get("username"));
        testContext.getDriver().findElement(By.name("password")).sendKeys(userCred.get("password"));
        testContext.getDriver().findElement(By.xpath("//input[@value='Log In']")).click();
    }

    @Then("User is able to login in the application")
    public void user_is_able_to_login_in_the_application() {
        Assert.assertEquals("ParaBank | Accounts Overview",testContext.getDriver().getTitle());
        boolean actualTableDisplayed = testContext.getDriver().findElement(By.id("accountTable")).isDisplayed();
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
        WebElement dropDownAccType = testContext.getDriver().findElement(By.id("type"));
        Select selectAccType = new Select(dropDownAccType);
        selectAccType.selectByVisibleText(type);

        WebElement dropDownAccNumber = testContext.getDriver().findElement(By.id("fromAccountId"));
        Select selectAccNumber = new Select(dropDownAccNumber);
        selectAccType.selectByVisibleText(accNumber);
    }


    //Temp Steps
    @Given("I want to do smthing")
    public void i_want_to_do_smthing() {

    }
    @When("I have a argument to send as {string}")
    public void i_have_a_argument_to_send_as(String arg) {
        System.out.println("Printing the Argument: " + arg);
    }
    @Then("something should happen")
    public void something_should_happen() {

    }

    @When("I have a list of items to send")
    public void i_have_a_list_of_items_to_send(List<String> list) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        System.out.println("Akash: " + list.toString());
    }

    @When("I have students and their marks")
    public void i_have_students_and_their_marks(Map<String, String> map) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        System.out.println(map);
    }

    //Exmplaes steps:

    @Given("I am on the search page")
    public void i_am_on_the_search_page() {

    }
    @When("I search for a product as {string}")
    public void i_search_for_a_product_as(String string) {
        System.out.println("Product searched: " + string);
    }
    @Then("result should be displayed related to {string}")
    public void result_should_be_displayed_related_to(String string) {
        System.out.println("Product search sucess: " + string);
    }
}



