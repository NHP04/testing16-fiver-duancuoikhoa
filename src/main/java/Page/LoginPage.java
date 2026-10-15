package Page;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import utils.ScreenshotUtil;


public class LoginPage extends BasePage {
    private static final By EMAIL_INPUT = By.id("email");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.xpath("//button[@type='submit']");

    private static final By EMAIL_ERROR_MESSAGE = By.xpath("//span[text()='Email không đúng định dạng !']");
    private static final By PASSWORD_LENGTH_ERROR_MESSAGE = By.xpath("//span[text()='pass từ 6 - 32 ký tự !']");
    private static final By EMAIL_EMPTY_MESSAGE = By.xpath("//span[text()='Email không được bỏ trống !']");
    private static final By PASSWORD_EMPTY_MESSAGE = By.xpath("//span[text()='Password không được bỏ trống !']");

    public LoginPage (WebDriver driver, WebDriverWait wait){
        super(driver,wait);
    }

    public void open(){
        Allure.step("Open login page", () -> {
            String url = ConfigReader.get("login.base.url");
            driver.get(url);
            wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_INPUT));
            ScreenshotUtil.takeScreenshot(driver, "login_page");
        });
    }

    public void enterEmail(String email) throws InterruptedException{
        Allure.step("Enter email: " + email, () -> {
            WebElement emailInput = driver.findElement(EMAIL_INPUT);
            highLight(emailInput);
            emailInput.sendKeys(email);
            ScreenshotUtil.takeScreenshot(driver, "enter_email");
            Thread.sleep(1000);
            unhightlight(emailInput);
        });
    }

    public void enterPassword(String password) throws InterruptedException{
        Allure.step("Enter password", () -> {
            WebElement passwordInput = driver.findElement(PASSWORD_INPUT);
            highLight(passwordInput);
            passwordInput.sendKeys(password);
            ScreenshotUtil.takeScreenshot(driver, "enter_password");
            Thread.sleep(1000);
            unhightlight(passwordInput);
        });
    }


    public void clickloginbtn() throws  InterruptedException{
        Allure.step("Click login button", () -> {
            WebElement loginbutton = driver.findElement(LOGIN_BUTTON);
            highLight(loginbutton);

            loginbutton.click();
            ScreenshotUtil.takeScreenshot(driver, "click_login_btn");

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

    public boolean isEmailErrorMessageDisplayed() {
        return driver.findElement(EMAIL_ERROR_MESSAGE).isDisplayed();
    }

    public boolean isPasswordLengthErrorMessageDisplayed() {
        return driver.findElement(PASSWORD_LENGTH_ERROR_MESSAGE).isDisplayed();
    }

    public boolean isEmailEmptyMessageDisplayed() {
        return driver.findElement(EMAIL_EMPTY_MESSAGE).isDisplayed();
    }

    public boolean isPasswordEmptyMessageDisplayed() {
        return driver.findElement(PASSWORD_EMPTY_MESSAGE).isDisplayed();
    }

}
