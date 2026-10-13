package tests;

import Page.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.CsvReader;

import java.io.IOException;
import java.util.List;

public class LoginTest extends BaseTest{

    @Test(description = "Test login success")
    public void testLoginSuccess () throws InterruptedException{
        LoginPage loginPage = new LoginPage(driver,wait);
        loginPage.login("Fiver04@gmail.com","fiver123");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("profile"));
        Assert.assertFalse(currentUrl.contains("login"));
    }

    @Test(description = "Test login fail wrong password")
    public void testLoginFail() throws InterruptedException{
        LoginPage Loginpage = new LoginPage(driver,wait);
        Loginpage.login("Fiver04@gmail.com", "fiver678");

        String currentUrl = driver.getCurrentUrl();
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
        LoginPage loginPage = new LoginPage(driver,wait);
        loginPage.login(email,password);

        if(expectedResult.equalsIgnoreCase("sucess")){
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("profile"));
        }else {
            Assert.assertTrue(driver.getCurrentUrl().contains("login"));
        }
    }

}
