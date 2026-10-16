package tests;

import Page.RegisterPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.BaseTest;
import utils.ConfigReader;
import utils.CsvReader;
import java.io.IOException;
import java.util.List;



@Epic("Authentication")
@Feature("Register")
public class RegisterTest extends BaseTest {

    @Story("Register success")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Register success with valid information")
    @Test(description = "Test register success")
//    public void testRegisterSuccess() throws InterruptedException {
//
//        RegisterPage registerPage = new RegisterPage(getDriver(), getWait());
//
//        registerPage.register(
//                ConfigReader.get("register.name"),
//                ConfigReader.get("register.email"),
//                ConfigReader.get("register.password"),
//                ConfigReader.get("register.passwordConfirm"),
//                ConfigReader.get("register.phone"),
//                ConfigReader.get("register.birthday"),
//                ConfigReader.get("register.gender"),
//                true
//        );
//
//        String currentUrl = getDriver().getCurrentUrl();
//
//        Assert.assertTrue(currentUrl.contains("login")
//                        || currentUrl.contains("profile"),
//                "Không chuyển đến trang tiếp theo sau khi đăng ký");
//    }

    @DataProvider(name = "registerData")
    public Object[][] registerDataProvider() throws IOException {
        String filePath = "src/test/resources/registerData.csv";

        List<String[]> data = CsvReader.readCsv(filePath);
        return CsvReader.toDataProviderArray(data);
    }

    @Test(
            description = "Test register data provider",
            dataProvider = "registerData"
    )
    public void testDataRegister(
            String name,
            String email,
            String password,
            String passwordConfirm,
            String phone,
            String birthday,
            String gender,
            String agree,
            String expectedResult
    ) throws InterruptedException {

        RegisterPage registerPage = new RegisterPage(getDriver(), getWait());

        registerPage.open();
        registerPage.enterName(name);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.enterPasswordConfirm(passwordConfirm);
        registerPage.enterPhone(phone);
        registerPage.enterBirthday(birthday);

        if ("male".equalsIgnoreCase(gender)) {
            registerPage.selectMale();
        } else if ("female".equalsIgnoreCase(gender)) {
            registerPage.selectFemale();
        }

        if ("true".equalsIgnoreCase(agree)) {
            registerPage.agreeTerm();
        }

        // Nhấn đăng ký
        registerPage.clickRegisterButton();

        switch (expectedResult.toLowerCase()) {

            case "name_empty":
                Assert.assertTrue(
                        registerPage.isNameEmptyMessageDisplayed()
                );
                break;

            case "email_empty":
                Assert.assertTrue(
                        registerPage.isEmailEmptyMessageDisplayed()
                );
                break;

            case "email_invalid":
                Assert.assertTrue(
                        registerPage.isEmailErrorMessageDisplayed()
                );
                break;

            case "password_empty":
                Assert.assertTrue(
                        registerPage.isPasswordEmptyMessageDisplayed()
                );
                break;

            case "password_length":
                Assert.assertTrue(
                        registerPage.isPasswordLengthErrorMessageDisplayed()
                );
                break;

            case "password_confirm_empty":
                Assert.assertTrue(
                        registerPage.isPasswordConfirmEmptyMessageDisplayed()
                );
                break;

            case "password_confirm_mismatch":
                Assert.assertTrue(
                        registerPage.isPasswordConfirmSameErrorMessageDisplayed()
                );
                break;

            case "birthday_empty":
                Assert.assertTrue(
                        registerPage.isBirthdayEmptyMessageDisplayed()
                );
                break;

            case "success":
                String currentUrl = getDriver().getCurrentUrl();

                Assert.assertTrue(
                        currentUrl.contains("login")
                                || currentUrl.contains("profile"),
                        "Đăng ký không thành công"
                );
                break;

            default:
                Assert.fail("expectedResult không hợp lệ: " + expectedResult);
        }
    }
}