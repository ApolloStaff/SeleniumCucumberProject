package StepDefinitions;

import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class StepDefinitions {

    WebDriver driver;
    WebDriverWait wait;

    private void waitFor(int seconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(d -> false);
        } catch (Exception ignored) {}
    }


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("guest", "--start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("User is on the store website")
    public void user_is_on_the_store_website() {
        driver.get("https://www.saucedemo.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
        waitFor(1);
    }

    @And("User has logged into his account with his {string} and {string}")
    public void userHasLoggedIntoHisAccountWithHisUsernameAndPassword(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
        waitFor(1);
    }

    @When("User adds multiple items available on the store page")
    public void user_adds_multiple_items_available_on_the_store_page() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        driver.findElement(By.id("shopping_cart_container")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout")));
        waitFor(1);
    }

    @And("Completes the purchase process")
    public void completes_the_purchase_process() {
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("user");
        driver.findElement(By.id("last-name")).sendKeys("last-name");
        driver.findElement(By.id("postal-code")).sendKeys("110011");
        driver.findElement(By.id("continue")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
        waitFor(1);
    }

    @Then("User is shown the checkout complete page")
    public void user_is_shown_the_checkout_complete_page() {
        driver.findElement(By.id("finish")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
        waitFor(1);
    }
}
