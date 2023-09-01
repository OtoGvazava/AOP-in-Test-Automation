package com.example.aopintestautomation;

import com.example.aopintestautomation.data.HardData;
import com.example.aopintestautomation.drivers.Driver;
import com.example.aopintestautomation.ui.pages.LoginPage;
import com.example.aopintestautomation.ui.pages.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class BaseTest {
    protected static WebDriver driver;
    protected static LoginPage loginPage;
    protected static MainPage mainPage;


    @BeforeSuite
    public void initProject() {
        WebDriverManager.chromedriver().setup();
        driver = Driver.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @BeforeTest
    public void initPages() {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
    }

    @BeforeClass
    public void makeAuth() {
        driver.get(HardData.BASE_URL);
        loginPage.inputUsername(HardData.USERNAME)
                .inputPassword(HardData.PASSWORD)
                .login();
    }

    public void takeScreenShot(String name) {
        Allure.addAttachment(name, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @AfterMethod()
    public void afterMethod(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.SUCCESS) {
                this.takeScreenShot(result.getName());
            } else if (result.getStatus() == ITestResult.FAILURE) {
                this.takeScreenShot(result.getName());
            } else if (result.getStatus() == ITestResult.SKIP) {
                this.takeScreenShot(result.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.takeScreenShot(result.getName());
        }
    }
}
