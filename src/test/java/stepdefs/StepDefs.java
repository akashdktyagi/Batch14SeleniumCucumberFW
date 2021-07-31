package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class StepDefs {

    WebDriver driver;
    String url = "https://parabank.parasoft.com/parabank/index.htm";
    String userName = "john";
    String password = "demo";

    @Given("User opened the browser")
    public void user_opened_the_browser() {
        driver = new ChromeDriver();
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
}



