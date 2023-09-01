package com.example.aopintestautomation;

import com.example.aopintestautomation.data.HardData;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class MainPageTest extends BaseTest{

    @Test(description = "Check footer text is correct")
    public void checkFooterText() {
        String expectedFooterText = HardData.FOOTER_TEXT;
        String actualFooterText = mainPage.getFooterText();
        Assert.assertEquals(actualFooterText, expectedFooterText, "Footer text is incorrect!");
    }

    @Test(description = "Check 'Add To Cart' buttons text")
    public void checkAddToCartText() {
        List<WebElement> buttons = mainPage.getAddToCartButtons();
        Assert.assertTrue(buttons.size() > 0);
        buttons.stream().forEach(button -> Assert.assertEquals(button.getText(),
                        "Add to cart",
                        "Button text is incorrect!"));
    }
}
