package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

public class ProfilePage extends BasePage {
//    private static final By PROFILE_BUTTON = By.xpath("(//a[@class='active'])[6]");
    private static final By PHONE_INPUT = By.xpath("//input[@name='phone']");
    private static final By NAME_INPUT = By.id("name");
    private static final By BIRTHDAY_INPUT = By.id("birthday");
    private static final By EDIT_BUTTON = By.xpath("(//button[@class='edit'])[1]");
    private static final By SAVE_BUTTON = By.xpath("//button[@type='submit']");

    public ProfilePage (WebDriver driver, WebDriverWait wait){
        super(driver,wait);
    }


    public void clickEditbtn() throws InterruptedException{
        WebElement editbutton = driver.findElement(EDIT_BUTTON);
        highLight(editbutton);
        editbutton.click();
        Thread.sleep(1000);
        unhightlight(editbutton);
    }

    public void enterName(String name) throws InterruptedException{
        WebElement nameInput = driver.findElement(NAME_INPUT);
        highLight(nameInput);
        nameInput.sendKeys(Keys.CONTROL, "a");
        nameInput.sendKeys(name);
        Thread.sleep(1000);
        unhightlight(nameInput);
    }

    public void enterPhone(String phone) throws InterruptedException{
        WebElement phoneInput = driver.findElement(PHONE_INPUT);
        highLight(phoneInput);
        phoneInput.sendKeys(Keys.CONTROL, "a");
        phoneInput.sendKeys(phone);
        Thread.sleep(1000);
        unhightlight(phoneInput);
    }

    public void enterBirthday(String birthday) throws InterruptedException{
        WebElement birthdayInput = driver.findElement(BIRTHDAY_INPUT);
        highLight(birthdayInput);
        birthdayInput.sendKeys(Keys.CONTROL, "a");
        birthdayInput.sendKeys(birthday);
        Thread.sleep(1000);
        unhightlight(birthdayInput);
    }

    public void clickSavebtn() throws InterruptedException{
        WebElement savebutton = driver.findElement(SAVE_BUTTON);
        highLight(savebutton);
        savebutton.click();
        Thread.sleep(1000);
        unhightlight(savebutton);
    }


    private static final By PHONE_TEXT =
            By.xpath("//h6[text()='Phone:']/following-sibling::p");


    public String getPhone() throws InterruptedException{
        WebElement phonetext = driver.findElement(PHONE_TEXT);
        return phonetext.getText();
    }

    public void edit (String phone, String name, String birthday)throws InterruptedException{
        clickEditbtn();
        enterPhone(phone);
        enterName(name);
        enterBirthday(birthday);
        clickSavebtn();
    }

}
