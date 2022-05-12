import locators.CoursesPageConstants;
import locators.Item;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CoursesPage {
    private WebDriver driver;
    CoursesPageConstants pageConstants = new CoursesPageConstants();


    public CoursesPage(WebDriver driver){
        this.driver = driver;
    }

    public void searchCourses(String searchText){
        System.out.println(driver.findElement(pageConstants.search_field_courses));
        for (Item course : pageConstants.courses) {
            System.out.println(course.name);
            System.out.println(course.price);
            System.out.println("printeeeed");
        }
        driver.findElement(pageConstants.search_field_courses).sendKeys(searchText);
        driver.findElement(pageConstants.search_button).click();
    }

    public Item[] getCourseData(){
        List<WebElement> titleElements = driver.findElements(pageConstants.product_title_classname);
        List<WebElement> priceElements = driver.findElements(pageConstants.product_price_classname);
        ArrayList<String> titles = new ArrayList<String>();
        ArrayList<String> prices = new ArrayList<String>();
        for(WebElement title: titleElements){
            titles.add(title.getText());
        }
        for(WebElement price: priceElements){
            prices.add(price.getText());
        }
        Item[] actualItems = new Item[8];
        for(int i = 0; i < actualItems.length; i++){
            actualItems[i] = new Item(titles.get(i), prices.get(i));
        }
        return actualItems;

    }

    public List<WebElement> getCoursesResults(){
        return driver.findElements(pageConstants.search_results_classname);
    }



}
