package com.example.aopintestautomation.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver DRIVER;
    private final By INPUT_USERNAME = By.xpath("//input[@id=\"user-name\"]");
    private final By INPUT_PASSWORD = By.xpath("//input[@id=\"password\"]");
    private final By INPUT_SUBMIT = By.xpath("//input[@id=\"login-button\"]");

    public LoginPage(WebDriver driver) {
        this.DRIVER = driver;
    }

    @Step("Input username")
    public LoginPage inputUsername(String username) {
        DRIVER.findElement(INPUT_USERNAME).sendKeys(username);
        return this;
    }

    @Step("Input password")
    public LoginPage inputPassword(String password) {
        DRIVER.findElement(INPUT_PASSWORD).sendKeys(password);
        return this;
    }

    @Step("Try login")
    public void login() {
        DRIVER.findElement(INPUT_SUBMIT).click();
    }
}
