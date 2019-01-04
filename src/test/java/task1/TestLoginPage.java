package task1;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestLoginPage {
    private WebDriver driver;
    private String baseUrl;
    private static LoginPage loginPage;
    private static BrokenLinks brokenLinks;

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
        System.setProperty("webdriver.chrome.driver", Util.CHROME_BROWSER);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        baseUrl = Util.BASE_URL;
        driver.manage().timeouts()
                .implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);

        driver.get(baseUrl + "V4/");
        loginPage = new LoginPage(driver);
        brokenLinks = new BrokenLinks(driver);
    }

    @Test(priority = 1)
    public void testLoginPage() throws IOException {
        loginPage.loginPage();
        loginPage.verifyLoginPage();
}

@Test(priority = 2)
public void searchBrokenLinks(){
        brokenLinks.searchBrokenLinks();
}
    @AfterClass
    public void tearDown() {

        driver.close();
    }
}