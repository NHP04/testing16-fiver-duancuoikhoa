package tests;

import Page.LoginPage;
import Page.ProfilePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ProfileTest extends BaseTest{
    private ProfilePage profilePage;

    @BeforeMethod
    private void loginAndOpenProfilePage() throws InterruptedException{
        LoginPage loginPage = new LoginPage(driver,wait);
        loginPage.login("Fiver04@gmail.com","fiver123");

        profilePage = new ProfilePage(driver,wait);


    }

    @Test(description = "Test edit profile success")
    public void testEditSuccess() throws InterruptedException{
        ProfilePage profilePage = new ProfilePage(driver,wait);

        String phone = "067432742";

        profilePage.edit( phone, "Phuoc42", "2004-05-25");

        Assert.assertTrue(profilePage.getPhone().equals(phone));

    }
}
