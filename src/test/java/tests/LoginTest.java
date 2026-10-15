package tests;

import Page.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ConfigReader;
import utils.CsvReader;

import java.io.IOException;
import java.util.List;

@Epic("Authencation")
@Feature("Login")
public class LoginTest extends BaseTest{

    @Story("Login success")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Login success with Fiver04@gmail.com email and **** password redirect to Profile page")
    @Test(description = "Test login success")
    public void testLoginSuccess () throws InterruptedException{
        LoginPage loginPage = new LoginPage(getDriver(),getWait());
        loginPage.login(ConfigReader.get("user.email"), ConfigReader.get("user.password"));

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("profile"));
        Assert.assertFalse(currentUrl.contains("login"));
    }

    @Test(description = "Test login fail wrong password")
    public void testLoginFail() throws InterruptedException{
        LoginPage loginPage = new LoginPage(getDriver(),getWait());
        loginPage.login("Fiver04@gmail.com", "fiver678");

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"));
        Assert.assertFalse(currentUrl.contains("profile"));
    }


    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() throws IOException {
        String filePath = "src/test/resources/loginData.csv";

        List<String[]> data = CsvReader.readCsv(filePath);
        return CsvReader.toDataProviderArray(data);
    }

    @Test(description = "Test login data provider", dataProvider = "loginData")
    public void testDataLogin(String email, String password, String expectedResult) throws InterruptedException{
        LoginPage loginPage = new LoginPage(getDriver(),getWait());
        loginPage.login(email,password);

        switch (expectedResult.toLowerCase()) {

            case "success":
                Assert.assertTrue(
                        getDriver().getCurrentUrl().contains("profile"));
                break;

            case "fail":
                Assert.assertTrue(
                        getDriver().getCurrentUrl().contains("login"));
                break;

            case "email_empty":
                Assert.assertTrue(loginPage.isEmailEmptyMessageDisplayed());
                break;

            case "email_invalid":
                Assert.assertTrue(loginPage.isEmailErrorMessageDisplayed());
                break;

            case "password_empty":
                Assert.assertTrue(loginPage.isPasswordEmptyMessageDisplayed());
                break;

            case "password_length":
                Assert.assertTrue(loginPage.isPasswordLengthErrorMessageDisplayed());
                break;

            case "both_empty":
                Assert.assertTrue(loginPage.isEmailEmptyMessageDisplayed());
                Assert.assertTrue(loginPage.isPasswordEmptyMessageDisplayed());
                break;

            default:
                Assert.fail("expectedResult không hợp lệ: " + expectedResult);
        }
    }

}
