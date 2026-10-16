package Page;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import utils.ScreenshotUtil;



public class RegisterPage extends BasePage {

    private static final By NAME_INPUT = By.id("name");
    private static final By EMAIL_INPUT = By.id("email");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By PASSWORDCONFIRM_INPUT = By.id("passwordConfirm");
    private static final By PHONE_INPUT = By.id("phone");
    private static final By BIRTHDAY_INPUT = By.id("birthday");
    private static final By AGREETERM_INPUT = By.id("agree-term");
    private static final By REGISTER_BUTTON = By.xpath("//button[@type='submit']");
    private static final By MALE_BUTTON = By.xpath("//label[@for='male']");
    private static final By FEMALE_BUTTON = By.xpath("//label[@for='female']");

    private static final By NAME_EMPTY_MESSAGE =
            By.xpath("//span[text()=' Name không được bỏ trống ']");

    private static final By EMAIL_ERROR_MESSAGE =
            By.xpath("//span[text()=' Email không đúng định dạng ']");
    private static final By EMAIL_EMPTY_MESSAGE =
            By.xpath("//span[text()='  Email không được bỏ trống ']");

    private static final By PASSWORD_LENGTH_ERROR_MESSAGE =
            By.xpath("//span[text()=' Password từ 6 - 32 ký tự ']");
    private static final By PASSWORD_EMPTY_MESSAGE =
            By.xpath("//span[text()='  Password không được bỏ trống ']");

    private static final By PASSWORD_CONFIRM_SAME_ERROR_MESSAGE =
            By.xpath("//span[text()='  Password phải trùng nhau ']");
    private static final By PASSWORDCONFIRM_EMPTY_MESSAGE =
            By.xpath("//span[text()='  PasswordConfirm không được bỏ trống ']");

    private static final By BIRTHDAY_EMPTY_MESSAGE =
            By.xpath("//span[text()='Birthday không được bỏ trống']");

    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open() {
        Allure.step("Open register page", () -> {
            String url = ConfigReader.get("register.base.url");
            driver.get(url);

            wait.until(ExpectedConditions.visibilityOfElementLocated(NAME_INPUT));
            ScreenshotUtil.takeScreenshot(driver, "register_page");
        });
    }

    public void enterName(String name) throws InterruptedException {
        Allure.step("Enter name: " + name, () -> {
            WebElement nameInput = driver.findElement(NAME_INPUT);
            highLight(nameInput);
            nameInput.click();
            nameInput.clear();
            nameInput.sendKeys(name);
            ScreenshotUtil.takeScreenshot(driver, "enter_name");
            Thread.sleep(1000);
            unhightlight(nameInput);
        });
    }

    public void enterEmail(String email) throws InterruptedException {
        Allure.step("Enter email: " + email, () -> {
            WebElement emailInput = driver.findElement(EMAIL_INPUT);
            highLight(emailInput);
            emailInput.click();
            emailInput.clear();
            emailInput.sendKeys(email);
            ScreenshotUtil.takeScreenshot(driver, "enter_email");
            Thread.sleep(1000);
            unhightlight(emailInput);
        });
    }

    public void enterPassword(String password) throws InterruptedException {
        Allure.step("Enter password", () -> {
            WebElement passwordInput = driver.findElement(PASSWORD_INPUT);
            highLight(passwordInput);
            passwordInput.click();
            passwordInput.clear();
            passwordInput.sendKeys(password);
            ScreenshotUtil.takeScreenshot(driver, "enter_password");
            Thread.sleep(1000);
            unhightlight(passwordInput);
        });
    }

    public void enterPasswordConfirm(String passwordConfirm)
            throws InterruptedException {
        Allure.step("Enter password confirmation", () -> {
            WebElement passwordConfirmInput =
                    driver.findElement(PASSWORDCONFIRM_INPUT);
            highLight(passwordConfirmInput);
            passwordConfirmInput.click();
            passwordConfirmInput.clear();
            passwordConfirmInput.sendKeys(passwordConfirm);
            ScreenshotUtil.takeScreenshot(driver, "enter_password_confirm");
            Thread.sleep(1000);
            unhightlight(passwordConfirmInput);
        });
    }

    public void enterPhone(String phone) throws InterruptedException {
        Allure.step("Enter phone: " + phone, () -> {
            WebElement phoneInput = driver.findElement(PHONE_INPUT);
            highLight(phoneInput);
            phoneInput.click();
            phoneInput.clear();
            phoneInput.sendKeys(phone);
            ScreenshotUtil.takeScreenshot(driver, "enter_phone");
            Thread.sleep(1000);
            unhightlight(phoneInput);
        });
    }

    public void enterBirthday(String birthday) throws InterruptedException {
        Allure.step("Enter birthday: " + birthday, () -> {
            WebElement birthdayInput = driver.findElement(BIRTHDAY_INPUT);
            highLight(birthdayInput);
            birthdayInput.click();
            birthdayInput.clear();
            birthdayInput.sendKeys(birthday);
            ScreenshotUtil.takeScreenshot(driver, "enter_birthday");
            Thread.sleep(1000);
            unhightlight(birthdayInput);
        });
    }

    public void selectMale() throws InterruptedException {
        Allure.step("Select gender: Male", () -> {
            WebElement maleBtn = driver.findElement(MALE_BUTTON);
            highLight(maleBtn);
            maleBtn.click();
            ScreenshotUtil.takeScreenshot(driver, "select_male");
            Thread.sleep(1000);
            unhightlight(maleBtn);
        });
    }

    public void selectFemale() throws InterruptedException {
        Allure.step("Select gender: Female", () -> {
            WebElement femailBtn = driver.findElement(FEMALE_BUTTON);
            highLight(femailBtn);
            femailBtn.click();
            ScreenshotUtil.takeScreenshot(driver, "select_female");
            Thread.sleep(1000);
            unhightlight(femailBtn);
        });
    }

    public void agreeTerm() throws InterruptedException {
        Allure.step("Agree to terms of service", () -> {
            WebElement agreeTermInput = driver.findElement(AGREETERM_INPUT);
            highLight(agreeTermInput);

            if (!agreeTermInput.isSelected()) {
                agreeTermInput.click();
            }

            ScreenshotUtil.takeScreenshot(driver, "agree_term");
            Thread.sleep(1000);
            unhightlight(agreeTermInput);
        });
    }

    public void clickRegisterButton() throws InterruptedException {
        Allure.step("Click register button", () -> {
            WebElement registerBtn = driver.findElement(REGISTER_BUTTON);
            highLight(registerBtn);
            registerBtn.click();
            ScreenshotUtil.takeScreenshot(driver, "click_register_btn");
            Thread.sleep(2000);
            unhightlight(registerBtn);
        });
    }

    public void register(String name, String email, String password,
                         String passwordConfirm, String phone, String birthday,
                         String gender, boolean agree)
            throws InterruptedException {

        open();
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        enterPasswordConfirm(passwordConfirm);
        enterPhone(phone);
        enterBirthday(birthday);

        if ("male".equalsIgnoreCase(gender)) {
            selectMale();
        } else if ("female".equalsIgnoreCase(gender)) {
            selectFemale();
        }

        if (agree) {
            agreeTerm();
        }

        clickRegisterButton();
    }

    public boolean isNameEmptyMessageDisplayed() {
        return driver.findElement(NAME_EMPTY_MESSAGE).isDisplayed();
    }

    public boolean isEmailErrorMessageDisplayed() {
        return driver.findElement(EMAIL_ERROR_MESSAGE).isDisplayed();
    }

    public boolean isEmailEmptyMessageDisplayed() {
        return driver.findElement(EMAIL_EMPTY_MESSAGE).isDisplayed();
    }

    public boolean isPasswordLengthErrorMessageDisplayed() {
        return driver.findElement(PASSWORD_LENGTH_ERROR_MESSAGE).isDisplayed();
    }

    public boolean isPasswordEmptyMessageDisplayed() {
        return driver.findElement(PASSWORD_EMPTY_MESSAGE).isDisplayed();
    }

    public boolean isPasswordConfirmSameErrorMessageDisplayed() {
        return driver.findElement(PASSWORD_CONFIRM_SAME_ERROR_MESSAGE)
                .isDisplayed();
    }

    public boolean isPasswordConfirmEmptyMessageDisplayed() {
        return driver.findElement(PASSWORDCONFIRM_EMPTY_MESSAGE).isDisplayed();
    }

    public boolean isBirthdayEmptyMessageDisplayed() {
        return driver.findElement(BIRTHDAY_EMPTY_MESSAGE).isDisplayed();
    }
}