package Page;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;


public class LoginPage extends BasePage {
    private static final By EMAIL_INPUT = By.id("email");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.xpath("//button[@type='submit']");


    public LoginPage (WebDriver driver, WebDriverWait wait){
        super(driver,wait);
    }

    public void open(){
        Allure.step("Open login page", () -> {
            String url = ConfigReader.get("login.base.url");
            driver.get(url);
            wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_INPUT));
        });
    }

    public void enterEmail(String email) throws InterruptedException{
        Allure.step("Enter email: " + email, () -> {
            WebElement emailInput = driver.findElement(EMAIL_INPUT);
            highLight(emailInput);
            emailInput.sendKeys(email);
            Thread.sleep(1000);
            unhightlight(emailInput);
        });
    }

    public void enterPassword(String password) throws InterruptedException{
        Allure.step("Enter password", () -> {
            WebElement passwordInput = driver.findElement(PASSWORD_INPUT);
            highLight(passwordInput);
            passwordInput.sendKeys(password);
            Thread.sleep(1000);
            unhightlight(passwordInput);
        });
    }


    public void clickloginbtn() throws  InterruptedException{
        Allure.step("Click login button", () -> {
            WebElement loginbutton = driver.findElement(LOGIN_BUTTON);
            highLight(loginbutton);
            loginbutton.click();
            Thread.sleep(2000);
            unhightlight(loginbutton);
        });
    }


    public void login (String email, String password) throws InterruptedException{
        open();
        enterEmail(email);
        enterPassword(password);
        clickloginbtn();
    }

}
