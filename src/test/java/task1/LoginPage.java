package task1;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class LoginPage {
    private WebDriver driver;
    private String baseUrl;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", Util.CHROME_BROWSER1);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        baseUrl = Util.BASE_URL;
        driver.manage().timeouts()
                .implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);

        driver.get(baseUrl + "V4/");
    }

    @Test
    public void loginPage() {
        String actualTitle;
        String actualBoxMsg;

        driver.findElement(By.name("uid")).clear();
        driver.findElement(By.name("uid")).sendKeys(Util.USER_ID);

        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(Util.PASSWORD);

        driver.findElement(By.name("btnLogin")).click();

        try {
            Alert alt = driver.switchTo().alert();
            actualBoxMsg = alt.getText();
            alt.accept();
            assertEquals(actualBoxMsg, Util.EXPECT_ERROR);
        } catch (NoAlertPresentException Ex) {
            actualTitle = driver.getTitle();
            assertEquals(actualTitle, Util.EXPECTED_TITLE);
        }
    }

    @AfterClass
    public void tearDown() {

        driver.close();
    }
}

