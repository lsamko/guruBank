package task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class LoginPage {
    static WebDriver driver;
    private static String baseUrl;
    public static void setUp() throws Exception {

        System.setProperty("webdriver.chrome.driver", Util.CHROME_BROWSER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        baseUrl = Util.BASE_URL;
//        driver.manage().timeouts()
//                .implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);

        driver.get(baseUrl + "V4/");
    }

    public static void main(String[] args) throws Exception {
               setUp();

        driver.findElement(By.name("uid")).clear();
        driver.findElement(By.name("uid")).sendKeys(Util.USER_ID);

        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(Util.PASSWORD);

        driver.findElement(By.name("btnLogin")).click();

        String actualResult = driver.getTitle();


        if (actualResult.contains( Util.EXPECTED_RESULT)) {
            System.out.println("Success!");
        } else {
            System.out.println("Try again");
        }

        driver.close();
    }
}
