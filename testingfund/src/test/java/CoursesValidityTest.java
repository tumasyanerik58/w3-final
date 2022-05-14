import com.google.common.io.Files;
import locators.BaseTestConstans;
import locators.CoursesPageConstants;
import mockData.Item;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.CoursesPage;
import pages.MainPage;

import java.io.File;
import java.io.IOException;

public class CoursesValidityTest {
    private static WebDriver driver;
    protected static MainPage mainPage;
    protected static CoursesPage coursesPage;
    BaseTestConstans testConstants = new BaseTestConstans();
    CoursesPageConstants pageConstants = new CoursesPageConstants();

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
    public void checkItemsValidity(){
        coursesPage = mainPage.goToCourses();
        Item[] actualResults = coursesPage.getCourseData();
        Item[] expectedResults = pageConstants.courses;
        for(int i = 0; i < actualResults.length;i++){
            assert(actualResults[i].name.contains(expectedResults[i].name));
            assert(actualResults[i].price.contains(expectedResults[i].price));
        }
    }

    @AfterClass
    public void cleanUp(){
        driver.quit();
    }
}
