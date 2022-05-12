import locators.LoginPageConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;
    LoginPageConstants pageConstants = new LoginPageConstants();

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void goToRegistration(){
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[4]/div[1]/div/div[2]/form/div[1]/div[1]/span/span")).click();
    }

    public void testCredentialsLessThanEight(){
        driver.findElement(pageConstants.username_input).sendKeys("test3@test.com");
        driver.findElement(pageConstants.password_input).sendKeys("Asdf3!", Keys.ENTER);
    }

    public void testCredentialsNoSpecial(){
        driver.findElement(pageConstants.username_input).sendKeys("test3@test.com");
        driver.findElement(pageConstants.password_input).sendKeys("Asdf1233", Keys.ENTER);
    }

    public void testCredentialsNoNumber(){
        driver.findElement(pageConstants.username_input).sendKeys("test3@test.com");
        driver.findElement(pageConstants.password_input).sendKeys("Asdfasdf!", Keys.ENTER);
    }

    public void testCredentialsNoUpperCase(){
        driver.findElement(pageConstants.username_input).sendKeys("test3@test.com");
        driver.findElement(pageConstants.password_input).sendKeys("asdf123!", Keys.ENTER);
    }

    public void testCredentialsNoLowerCase(){
        driver.findElement(pageConstants.username_input).sendKeys("test3@test.com");
        driver.findElement(pageConstants.password_input).sendKeys("ASDF123!", Keys.ENTER);
    }

    public void testCredentialsWrongEmail(){
        driver.findElement(pageConstants.username_input).sendKeys("wrongemail");
        driver.findElement(pageConstants.password_input).sendKeys("Asdf123!", Keys.ENTER);
    }

    public void testCorrectCredentialsNoFirstName(){
        driver.findElement(pageConstants.username_input).sendKeys("rightest@mail.com");
        driver.findElement(pageConstants.password_input).sendKeys("Asdf123!", Keys.ENTER);
        driver.findElement(pageConstants.button_xpath).click();
    }

    public void testCorrectCredentialsNoLastName(){
        driver.findElement(pageConstants.username_input).sendKeys("rightest@mail.com");
        driver.findElement(pageConstants.password_input).sendKeys("Asdf123!", Keys.ENTER);
        driver.findElement(pageConstants.first_name_input).sendKeys("firstname");
        driver.findElement(pageConstants.button_xpath).click();
    }

    public void testCorrectCredentials(){
        driver.findElement(pageConstants.username_input).sendKeys("rightest@mail.com");
        driver.findElement(pageConstants.password_input).sendKeys("Asdf123!", Keys.ENTER);
        driver.findElement(pageConstants.first_name_input).sendKeys("firstname");
        driver.findElement(pageConstants.last_name_input).sendKeys("lastname");
        driver.findElement(pageConstants.button_xpath).click();
    }


    public WebElement getPasswordInput(){
        return driver.findElement(pageConstants.password_input);
    }

    public WebElement getEmailInput(){
        return driver.findElement(pageConstants.username_input);
    }

    public WebElement getFirstName(){
        return driver.findElement(pageConstants.first_name_input);
    };

    public WebElement getLastName(){
        return driver.findElement(pageConstants.last_name_input);
    };





}
