package task1;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginPage extends DriverSetup {
    protected LoginPage(WebDriver driver) {
        super(driver);
    }

    private String actualBoxMsg;

    public void loginPage() {

        driver.findElement(By.name("uid")).clear();
        driver.findElement(By.name("uid")).sendKeys(Util.USER_ID);

        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(Util.PASSWORD);

        driver.findElement(By.name("btnLogin")).click();
    }

    public void verifyLoginPage() throws IOException {
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
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // Code to save screenshot at desired location
            FileUtils.copyFile(srcFile, new File("C:\\Users\\l.samko\\Pictures\\screenshot.png"));

        }
    }

}

