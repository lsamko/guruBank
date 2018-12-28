package guruBank;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;

public class NewProject {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\maven\\chromedriver.exe" );
        WebDriver driver = new ChromeDriver();
        String baseUrl =  "http://demo.guru99.com/test/newtours/";
        String expectedTitle = "Welcome: Mercury Tours";
        String actualTitle = " ";

        driver.get(baseUrl);
        actualTitle = driver.getTitle();

        if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
        }

        driver.close();
    }
}
