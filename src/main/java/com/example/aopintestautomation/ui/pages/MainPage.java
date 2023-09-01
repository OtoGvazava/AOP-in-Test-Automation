package com.example.aopintestautomation.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage {
    protected final WebDriver DRIVER;
    private final By TEXT_FOOTER = By.xpath("//div[@id=\"page_wrapper\"]/footer/div");
    private final By BUTTONS_ADD_TO_CART = By.xpath("//div[@class='inventory_item']//button");

    public MainPage(WebDriver driver) {
        DRIVER = driver;
    }
    @Step("Get footer text")
    public String getFooterText() {
        return DRIVER.findElement(TEXT_FOOTER).getText();
    }

    @Step("Get add to cart buttons")
    public List<WebElement> getAddToCartButtons() {
        return DRIVER.findElements(BUTTONS_ADD_TO_CART);
    }
}