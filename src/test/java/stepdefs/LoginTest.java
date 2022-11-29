package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginTest {

    WebDriver driver;

    @Given("user navigates to {string}")
    public void navigate(String webpage) {
        driver = new ChromeDriver();
        driver.get(webpage);
    }

    @Then("user clicks on {string} and link {string}")
    public void user_clicks_on_form_authentication(String linkText, String link) {
        driver.findElement(By.xpath(String.format("//a[normalize-space(.)=\"%s\" and contains(@href,\"%s\")]", linkText, link))).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]")));

    }

    @When("user logs in using username as {string} and password {string}")
    public void user_login(String username, String password) {
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(username);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
        driver.findElement(By.xpath("//i[contains(text(), \"Login\")]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[contains(text(),\"Logout\")]")));
    }

    @Then("login should be successful")
    public void successful_login() {
        driver.findElement(By.xpath("//i[contains(text(),\"Logout\")]"));
    }
}
