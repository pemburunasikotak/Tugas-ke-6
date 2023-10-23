package kasirAja.cocumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class order {
    WebDriver driver;
    String baseUrl = "https://kasirdemo.belajarqa.com/";
    @Given("Halaman login kasir aja test")
    public void halamanLoginKasirAjaTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);
//        String loginPageAssert = driver.findElement(By.xpath("//h2[contains(text(),'hai, kasirAja')]")).getText();
//        Assert.assertEquals(loginPageAssert,"hai, kasirAja");
    }

    @When("Input username test")
    public void inputUsernameTest() {
    }

    @And("Input password tes")
    public void inputPasswordTes() {
    }

    @And("Click Login button tes")
    public void clickLoginButtonTes() {
    }

    @Then("user in dashboard page test")
    public void userInDashboardPageTest() {
    }
}
