import com.google.common.io.Files;
import locators.BaseTestConstans;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.MainPage;

import java.io.File;
import java.io.IOException;

public class RegistrationTest {
    private static WebDriver driver;
    protected static MainPage mainPage;
    protected static LoginPage loginPage;
    BaseTestConstans testConstants = new BaseTestConstans();

    @BeforeClass
    public WebDriver setUp(){
        System.setProperty(testConstants.driverProperty,testConstants.driverPath);
        driver = new ChromeDriver();
        driver.get(testConstants.url_main);
        mainPage = new MainPage(driver);
        return driver;
    }

    @BeforeMethod
    public void refresh(){
        driver.get(testConstants.url_main);
    }

    @AfterMethod
    public void recordFailure(ITestResult result){
        if(ITestResult.FAILURE == result.getStatus()) {
            var camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try{
                Files.move(screenshot, new File("resources/screenshots/" + result.getName() + ".png"));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    @Test
    public void checkRegistrationLessThanEight(){
        loginPage = mainPage.goToLoginPage();
        loginPage.goToRegistration();
        loginPage.testCredentials("test3@test.com","Asdf3!");
        WebElement passwordInput = loginPage.getPasswordInput();
        assert(passwordInput.getCssValue("border-color").equals("rgb(217, 33, 44)"));
    }

    @Test
    public void CheckRegistrationNoSpecial(){
        loginPage = mainPage.goToLoginPage();
        loginPage.goToRegistration();
        loginPage.testCredentials("test3@test.com","Asdf1233");
        WebElement passwordInput = loginPage.getPasswordInput();
        assert(passwordInput.getCssValue("border-color").equals("rgb(217, 33, 44)"));
    }

    @Test
    public void CheckRegistrationNoNumber(){
        loginPage = mainPage.goToLoginPage();
        loginPage.goToRegistration();
        loginPage.testCredentials("test3@test.com","Asdfasdf!");
        WebElement passwordInput = loginPage.getPasswordInput();
        assert(passwordInput.getCssValue("border-color").equals("rgb(217, 33, 44)"));
    }

    @Test
    public void CheckRegistrationNoUpperCase(){
        loginPage = mainPage.goToLoginPage();
        loginPage.goToRegistration();
        loginPage.testCredentials("test3@test.com","asdf123!");
        WebElement passwordInput = loginPage.getPasswordInput();
        assert(passwordInput.getCssValue("border-color").equals("rgb(217, 33, 44)"));
    }

    @Test
    public void CheckRegistrationNoLowerCase(){
        loginPage = mainPage.goToLoginPage();
        loginPage.goToRegistration();
        loginPage.testCredentials("test3@test.com","ASDF123!");
        WebElement passwordInput = loginPage.getPasswordInput();
        assert(passwordInput.getCssValue("border-color").equals("rgb(217, 33, 44)"));
    }

    @Test
    public void CheckRegistrationWrongEmail(){
        loginPage = mainPage.goToLoginPage();
        loginPage.goToRegistration();
        loginPage.testCredentials("wrongemail","Asdf123!");
        WebElement emailInput = loginPage.getEmailInput();
        assert(emailInput.getCssValue("border-color").equals("rgb(217, 33, 44)"));
    }

    @Test
    public void CheckRegistrationNoFirstName(){
        loginPage = mainPage.goToLoginPage();
        loginPage.goToRegistration();
        loginPage.testCorrectCredentialsNoFirstName();
        WebElement firstNameInput = loginPage.getFirstName();
        assert(firstNameInput.getCssValue("border-color").equals("rgb(217, 33, 44)"));
    }

    @Test
    public void CheckRegistrationNoLastName(){
        loginPage = mainPage.goToLoginPage();
        loginPage.goToRegistration();
        loginPage.testCorrectCredentialsNoLastName();
        WebElement lastNameInput = loginPage.getLastName();
        assert(lastNameInput.getCssValue("border-color").equals("rgb(217, 33, 44)"));
    }

    @Test
    public void CheckRegistration(){
        loginPage = mainPage.goToLoginPage();
        loginPage.goToRegistration();
        loginPage.testCorrectCredentials();
        // no asserts in here as you requested to skip the last step of registration
    }

    @AfterClass
    public void cleanUp(){
        driver.quit();
    }
}
