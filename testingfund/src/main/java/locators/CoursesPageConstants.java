package locators;

import org.openqa.selenium.By;


public class CoursesPageConstants {
    public By search_field_courses = By.id("search");
    public By search_button = By.id("search-submit");
    public By search_results_classname = By.className("product-description");
    public By product_title_classname = By.className("product-title");
    public By product_price_classname = By.className("product-price");
    public Item[] courses = ItemsList.getItems();
}
