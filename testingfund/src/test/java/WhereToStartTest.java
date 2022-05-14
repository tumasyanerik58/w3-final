import com.google.common.io.Files;
import locators.BaseTestConstans;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.MainPage;
import pages.WtsPage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WhereToStartTest {
    private static WebDriver driver;
    protected static MainPage mainPage;
    protected static WtsPage wtsPage;
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
    public void checkWtsHtml(){
        wtsPage = mainPage.goToWtsPage();
        wtsPage.goToLearnHTML();
        String currentUrl = driver.getCurrentUrl();
        assert(currentUrl.contains("html_intro"));
    }

    @Test
    public void checkWtsCss(){
        wtsPage = mainPage.goToWtsPage();
        wtsPage.goToCss();
        String currentUrl = driver.getCurrentUrl();
        assert(currentUrl.contains("css_intro"));
    }

    @Test
    public void checkWtsJs(){
        wtsPage = mainPage.goToWtsPage();
        wtsPage.goToJs();
        String currentUrl = driver.getCurrentUrl();
        assert(currentUrl.contains("js_intro"));
    }

    @Test
    public void checkWtsSpaces(){
        wtsPage = mainPage.goToWtsPage();
        wtsPage.goToSpaces();
        String currentUrl = driver.getCurrentUrl();
        assert(currentUrl.contains("spaces"));
    }

    @Test
    public void checkHeaders(){
        wtsPage = mainPage.goToWtsPage();
        ArrayList<String> headers = wtsPage.getHeaders();
        assert(headers.get(0).contains("HTML"));
        assert(headers.get(1).contains("CSS"));
        assert(headers.get(2).contains("JavaScript"));
    }

    @AfterClass
    public void cleanUp(){
        driver.quit();
    }
}
