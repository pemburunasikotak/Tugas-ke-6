package kasirAja;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import config.env;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.logging.Log;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginTDD extends env {
    //Login menggunakan fitur data driven test
    @Test
    public void login_ddt(){
        Login login = new Login();
        login.success_login_case();
        login.failed_login_case();
        WebDriver driver;
//        String baseUrl = "https://kasirdemo.belajarqa.com/";

        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.setHeadless(false);

        String csvDir = System.getProperty("user.dir")+"/src/test/data/test-data.csv";

        try (CSVReader reader= new CSVReader(new FileReader(csvDir))){
            String[] nextLine ;
            while ((nextLine=reader.readNext())!=null){
                String email = nextLine[0];
                String password = nextLine[1];
                String status = nextLine[2];

                driver = new ChromeDriver(opt);
                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                driver.get(url);

//                Iterasi Elemen
                //pengisian form
                driver.findElement(By.id("email")).sendKeys(email);
                driver.findElement(By.id("password")).sendKeys(password);
                driver.findElement(By.xpath("//button[@type='submit']")).click();

                if(status.equals("success")){
                    driver.findElement(By.xpath("//div[contains(text(),'dashboard']"));
                    String userName = driver.findElement(By.xpath("//dd[contains(text(),'hai')]/preceding-sibling::dt")).getText();
                    Assert.assertEquals(userName, "ttd-selenium");
                }else {
                    String errorLogin = driver.findElement(By.xpath("//div[@role='alert']")).getText();
                    Assert.assertEquals(errorLogin, "Kredensial yang Anda berikan salah");
                }
                driver.close();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
