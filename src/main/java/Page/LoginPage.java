package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage extends BasePage {
    private static final By EMAIL_INPUT = By.id("email");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.xpath("//button[@type='submit']");


    public LoginPage (WebDriver driver, WebDriverWait wait){
        super(driver,wait);
    }

    public void open(){
        String url = "https://demo4.cybersoft.edu.vn/login";
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_INPUT));
    }

    public void enterEmail(String email) throws InterruptedException{
        WebElement emailInput = driver.findElement(EMAIL_INPUT);
        highLight(emailInput);
        emailInput.sendKeys(email);
        Thread.sleep(2000);
        unhightlight(emailInput);
    }

    public void enterPassword(String password) throws InterruptedException{
        WebElement passwordInput = driver.findElement(PASSWORD_INPUT);
        highLight(passwordInput);
        passwordInput.sendKeys(password);
        Thread.sleep(2000);
        unhightlight(passwordInput);
    }


    public void clickloginbtn() throws  InterruptedException{
        WebElement loginbutton = driver.findElement(LOGIN_BUTTON);
        highLight(loginbutton);
        loginbutton.click();
        Thread.sleep(2000);
        unhightlight(loginbutton);
    }


    public void login (String email, String password) throws InterruptedException{
        open();
        enterEmail(email);
        enterPassword(password);
        clickloginbtn();
    }

}
