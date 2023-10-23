package kasirAja;



import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import config.env;

public class Login extends env {
    @Test
    public void success_login_case(){
        WebDriver driver;
//        String baseUrl = "https://kasirdemo.belajarqa.com/";

//        WebDriverManager.chromedriver().setup();

        //apply chrome driver
        //membuka halaman login
        driver = WebDriverManager.chromedriver().create();
        driver.manage().window().maximize();
        driver.get(url);

        String loginPageAssert = driver.findElement(By.xpath("//h2[contains(text(),'hai, kasirAja')]")).getText();
        Assert.assertEquals(loginPageAssert,"hai, kasirAja");

        //input email
        driver.findElement(By.id("email")).sendKeys("admin@sel.com");
        //input password
        driver.findElement(By.id("password")).sendKeys("test321");
        //click login
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //asert nama toko
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//dt[contains(text(),'Sel Ventures')]")));
        String namaToko = driver.findElement(By.xpath("//dt[contains(text(),'Sel Ventures')]")).getText();
        Assert.assertEquals(namaToko, "Sel Ventures");
        //quit
        driver.close();
    }

    @Test
    public void failed_login_case(){
        WebDriver driver;
        String baseUrl = "https://kasirdemo.belajarqa.com/";

        WebDriverManager.chromedriver().setup();

        //apply chrome driver
        //membuka halaman login
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);

        String loginPageAssert = driver.findElement(By.xpath("//h2[contains(text(),'hai, kasirAja')]")).getText();
        Assert.assertEquals(loginPageAssert,"hai, kasirAja");
        //input email
        driver.findElement(By.id("email")).sendKeys("admin@sel.com");
        //input password
        driver.findElement(By.id("password")).sendKeys("321123");
        //click login
        driver.findElement(By.xpath("//button[@type='submit']")).click();
//        String ErrorLogin = driver.findElement(By.xpath("//div[@role='alart']")).getText();
//        Assert.assertEquals(ErrorLogin, "Kredensial yang Anda berikan salah");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']")));
        String errorLogin = errorElement.getText();
        Assert.assertEquals(errorLogin, "Kredensial yang Anda berikan salah");

        driver.close();
    }

}
