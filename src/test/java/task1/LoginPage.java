package task1;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginPage {
    private WebDriver driver;
    private String baseUrl;

    //create test data for testing The test data include set of username,
    //	 password

    @DataProvider
    public Object[][] testData() {
        Object[][] data = new Object[4][2];
        // 1st row
        data[0][0] = Util.USER_ID;
        data[0][1] = Util.PASSWORD;
        //2nd row
        data[1][0] = "invalid";
        data[1][1] = "valid";
        //3rd row
        data[2][0] = "valid";
        data[2][1] = "invalid";
        //4th row
        data[3][0] = "invalid";
        data[3][1] = "invalid";
        return data;
    }

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
    public void loginPage() throws IOException {
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
            // Get text displays on login page
            String pageText = driver.findElement(By.tagName("tbody")).getText();

            // Extract the dynamic text mngrXXXX on page
            String[] parts = pageText.split(Util.PATTERN);
            String dynamicText = parts[1];

            // Check that the dynamic text is of pattern mngrXXXX
            // First 4 characters must be "mngr"
            assertTrue(dynamicText.substring(1, 5).equals(Util.FIRST_PATTERN));

            // remain stores the "XXXX" in pattern mngrXXXX
            String remain = dynamicText.substring(dynamicText.length() - 4);

            // Check remain string must be numbers;
            assertTrue(remain.matches(Util.SECOND_PATTERN));

            // Code to take Screenshot
            File srcFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            // Code to save screenshot at desired location
            FileUtils.copyFile(srcFile, new File("C:\\Users\\Ludmila\\Desktop\\screenshot.png"));

        }
    }

    @AfterClass
    public void tearDown() {

        driver.close();
    }
}

