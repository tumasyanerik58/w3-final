import locators.MainPageConstans;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage {
    private WebDriver driver;
    MainPageConstans pageConstans = new MainPageConstans();


    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void search(String searchText){
        driver.findElement(pageConstans.search_field).sendKeys(searchText);
    }

    public List<WebElement> getResults(){
        return driver.findElements(pageConstans.results_classname);
    }

    public CoursesPage goToCourses(){
        driver.get(pageConstans.courses_url);
        return new CoursesPage(driver);
    }

    public LoginPage goToLoginPage(){
        driver.findElement(pageConstans.login_button).click();
        return new LoginPage(driver);
    }

    public WtsPage goToWtsPage(){
        driver.findElement(pageConstans.wts_linktext).click();
        return new WtsPage(driver);
    }

}
