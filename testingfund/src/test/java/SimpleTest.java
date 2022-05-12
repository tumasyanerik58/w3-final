import com.google.common.io.Files;
import locators.CoursesPageConstants;
import locators.Item;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleTest {
    private static WebDriver driver;
    protected static MainPage mainPage;
    protected static CoursesPage coursesPage;
    protected static LoginPage loginPage;
    protected static WtsPage wtsPage;
    CoursesPageConstants pageConstants = new CoursesPageConstants();
    private static String url_main = "https://www.w3schools.com/";
    private static String driverProperty = "webdriver.chrome.driver";
    private static String driverPath = "src/driver/chromedriver";

    @BeforeClass
    public WebDriver setUp(){
        System.setProperty(driverProperty,driverPath);
        driver = new ChromeDriver();
        driver.get(url_main);
        mainPage = new MainPage(driver);
        return driver;
    }

    @BeforeMethod
    public void refresh(){
        driver.get(url_main);
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
    public void checkSearchFundCourses(){
        coursesPage = mainPage.goToCourses();
        coursesPage.searchCourses("javascript");
        List<WebElement> search_results_courses = coursesPage.getCoursesResults();
        for (WebElement element : search_results_courses) {
            assert(element.getText().toLowerCase().contains("javascript"));
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

    @Test
    public void checkRegistrationLessThanEight(){
        loginPage = mainPage.goToLoginPage();
        loginPage.goToRegistration();
        loginPage.testCredentialsLessThanEight();
        WebElement passwordInput = loginPage.getPasswordInput();
        assert(passwordInput.getCssValue("border-color").equals("rgb(217, 33, 44)"));
    }

    @Test
    public void CheckRegistrationNoSpecial(){
        loginPage = mainPage.goToLoginPage();
        loginPage.goToRegistration();
        loginPage.testCredentialsNoSpecial();
        WebElement passwordInput = loginPage.getPasswordInput();
        assert(passwordInput.getCssValue("border-color").equals("rgb(217, 33, 44)"));
    }

    @Test
    public void CheckRegistrationNoNumber(){
        loginPage = mainPage.goToLoginPage();
        loginPage.goToRegistration();
        loginPage.testCredentialsNoNumber();
        WebElement passwordInput = loginPage.getPasswordInput();
        assert(passwordInput.getCssValue("border-color").equals("rgb(217, 33, 44)"));
    }

    @Test
    public void CheckRegistrationNoUpperCase(){
        loginPage = mainPage.goToLoginPage();
        loginPage.goToRegistration();
        loginPage.testCredentialsNoUpperCase();
        WebElement passwordInput = loginPage.getPasswordInput();
        assert(passwordInput.getCssValue("border-color").equals("rgb(217, 33, 44)"));
    }

    @Test
    public void CheckRegistrationNoLowerCase(){
        loginPage = mainPage.goToLoginPage();
        loginPage.goToRegistration();
        loginPage.testCredentialsNoLowerCase();
        WebElement passwordInput = loginPage.getPasswordInput();
        assert(passwordInput.getCssValue("border-color").equals("rgb(217, 33, 44)"));
    }

    @Test
    public void CheckRegistrationWrongEmail(){
        loginPage = mainPage.goToLoginPage();
        loginPage.goToRegistration();
        loginPage.testCredentialsWrongEmail();
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
