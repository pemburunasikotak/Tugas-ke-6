package kasirAja.cocumber.stepDef;

import config.env;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class login {
    WebDriver driver;
    String baseUrl = "https://kasirdemo.belajarqa.com/";

    @Given("Halaman login kasir aja")
    public void halamanLoginKasirAja() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get(baseUrl);
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        String loginPageAssert = driver.findElement(By.xpath("//h2[contains(text(),'hai, kasirAja')]")).getText();
        Assert.assertEquals(loginPageAssert,"hai, kasirAja");
    }

    @When("Input username")
    public void inputUsername() {
        driver.findElement(By.id("email")).sendKeys("tdd-selenium@gmail.com");
    }

    @And("Input password")
    public void inputPassword() {
        driver.findElement(By.id("password")).sendKeys("tdd-selenium");
    }

    @And("Click Login button")
    public void clickLoginButton() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("user in dashboard page")
    public void userInDashboardPage() {
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        String userName = driver.findElement(By.xpath("//dd[contains(text(),'hai')]/preceding-sibling::dt")).getText();
        Assert.assertEquals(userName, "tdd-selenium");
        driver.close();
    }

    @And("Input Invalid password")
    public void inputInvalidPassword() {
        driver.findElement(By.id("password")).sendKeys("test");
    }

    @Then("User get Error massage")
    public void userGetErrorMassage() {
        String errorLogin = driver.findElement(By.xpath("//div[@role='alert']")).getText();
        System.out.println("Test"+errorLogin);
        Assert.assertEquals(errorLogin, "Kredensial yang Anda berikan salah");
        driver.close();
    }

    @Then("close halaman")
    public void closeHalaman() {
        driver.close();
    }

    @When("I input (.*) as email$")
    public void iInputEmailAsEmail(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
    }

    @And("I input (.*) as password$")
    public void iInputPasswordAsPassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @Then("I verify (.*) login result$")
    public void iVerifyStatusLoginResult(String status) {
        if(status.equals("success")){
            String userName = driver.findElement(By.xpath("//dd[contains(text(),'hai')]/preceding-sibling::dt")).getText();
            Assert.assertEquals(userName, "tdd-selenium");
            driver.close();
        }else {
            String errorLogin = driver.findElement(By.xpath("//div[@role='alert']")).getText();
            Assert.assertEquals(errorLogin, "Kredensial yang Anda berikan salah");
            driver.close();
        }
    }
}
