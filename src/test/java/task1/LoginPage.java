package task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", Util.CHROME_BROWSER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        String expectedResult = "Welcome To Manager's Page of Guru99 Bank";
        String message = "Login successful";
        String actualResult = driver.getTitle();

        driver.get(Util.BASE_URL);
        driver.findElement(By.name("uid")).sendKeys(Util.USER_ID);
        driver.findElement(By.name("password")).sendKeys(Util.PASSWORD);
        driver.findElement(By.name("btnLogin")).click();


        if (actualResult.contentEquals( expectedResult)) {
            System.out.println(message);
        } else {
            System.out.println("Try again");
        }

        driver.close();
    }
}
