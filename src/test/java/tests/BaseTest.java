package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ScreenshotUtil;


import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class BaseTest {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> waitThreadLocal = new ThreadLocal<>();

    protected WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    protected WebDriverWait getWait() {
        return waitThreadLocal.get();
    }

    //    setup moi truong
    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) throws IOException {

        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName();

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String folderName = String.format(
                "%s_%s_%s_chrome",
                className, methodName, timestamp
        );

        String testFolderPath = "target/test-output/" + folderName;
        Files.createDirectories(Paths.get(testFolderPath));

        ScreenshotUtil.setTestFolder(testFolderPath);

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(30)
        );

        driverThreadLocal.set(driver);
        waitThreadLocal.set(wait);
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        WebDriver driver = getDriver();

        if(driver != null && result.getStatus() == ITestResult.FAILURE) {
            ScreenshotUtil.takeScreenshot(driver, "FAILED_" + result.getName());
        }

        if(driver != null){
            driver.quit();
        }

        ScreenshotUtil.clear();
        driverThreadLocal.remove();
        waitThreadLocal.remove();
    }
}