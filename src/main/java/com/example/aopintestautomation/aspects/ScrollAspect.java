package com.example.aopintestautomation.aspects;

import com.example.aopintestautomation.drivers.Driver;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

@Aspect
public class ScrollAspect {
    @Before("execution(* org.openqa.selenium.WebElement.*(..))")
    public void scrollElementIntoView(JoinPoint joinPoint) {
        WebElement element = (WebElement) joinPoint.getTarget();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) Driver.getDriver();

        boolean isVisible = (boolean) jsExecutor.executeScript(
                "var rect = arguments[0].getBoundingClientRect(); " +
                        "return (rect.top >= 0 && rect.left >= 0 && " +
                        "rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && " +
                        "rect.right <= (window.innerWidth || document.documentElement.clientWidth));", element);

        if (!isVisible) {
            jsExecutor.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center', inline: 'center' });", element);
        }
    }
}
