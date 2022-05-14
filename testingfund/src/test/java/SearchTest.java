import com.google.common.io.Files;
import locators.BaseTestConstans;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.CoursesPage;
import pages.MainPage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SearchTest {
    private static WebDriver driver;
    protected static MainPage mainPage;
    protected static CoursesPage coursesPage;
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
    public void checkSearchFunc(){
        mainPage.search("java");
        List<WebElement> search_results = mainPage.getResults();
        for (WebElement element : search_results) {
            assert(element.getText().toLowerCase().contains("java"));
        }
    }

    @Test
    public void checkSearchFuncCourses(){
        coursesPage = mainPage.goToCourses();
        coursesPage.searchCourses("javascript");
        List<WebElement> search_results_courses = coursesPage.getCoursesResults();
        for (WebElement element : search_results_courses) {
            assert(element.getText().toLowerCase().contains("javascript"));
        }
    }

    @AfterClass
    public void cleanUp(){
        driver.quit();
    }
}
