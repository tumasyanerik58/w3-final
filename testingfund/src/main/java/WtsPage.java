import locators.WtsPageConstants;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WtsPage {
    private WebDriver driver;
    WtsPageConstants pageConstants = new WtsPageConstants();


    public WtsPage(WebDriver driver){
        this.driver = driver;
    }

    public void goToLearnHTML(){
        driver.findElement(pageConstants.html_linktext).click();
    }

    public void goToCss(){
        driver.findElement(pageConstants.css_linktext).click();
    }

    public void goToJs(){
        driver.findElement(pageConstants.js_linktext).click();
    }

    public void goToSpaces(){   driver.findElement(pageConstants.spaces_linktext).click();  }

    public ArrayList<String> getHeaders(){
        List<WebElement> headerElements = driver.findElements(By.tagName("h2"));
        ArrayList<String> headers = new ArrayList<String>();
        for(WebElement element: headerElements){
            if(element.getText().length() > 0){
                headers.add(element.getText());
            }
        }
        return headers;
    }


}
