package com.browserstack.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.JavascriptExecutor;


import org.openqa.selenium.support.ui.ExpectedConditions;


import com.browserstack.BrowserStackTestNGTest;

public class SuiteTest06 extends BrowserStackTestNGTest {

    @Test
    public void test_06() throws Exception {
        driver.get("https://www.browserstack.com/");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        JavascriptExecutor jse = (JavascriptExecutor) driver;


        driver.manage().window().maximize();

        Thread.sleep(2000);
        driver.findElement(By.linkText("Sign in")).click();
        Thread.sleep(2000);

        String email = System.getenv("BROWSERSTACK_DEMO_USERNAME");
        String password = System.getenv("BROWSERSTACK_DEMO_PASSWORD");


        driver.findElement(By.id("user_email_login")).sendKeys(email);
        driver.findElement(By.id("user_password")).sendKeys(password);
        driver.findElement(By.id("user_submit")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.className("smalltext"))).click();

        driver.findElement(By.className("browser-version-list__element--selected")).click();
        driver.get("https://www.google.com/ncr");
        Thread.sleep(5000);
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("BrowserStack Test 06");
        element.submit();
        Thread.sleep(5000);
        System.out.println(driver.getTitle());
        try {
            wait.until(ExpectedConditions.titleContains("BrowserStack - Google Search"));
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"" + "passed" + "\", \"reason\": \"" + "Yaay title contains 'BrowserStack - Google Search" + "\"}}");
        } catch (Exception e) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"" + "failed" + "\", \"reason\": \"" + "Naay title does not contain 'BrowserStack - Google Search" + "\"}}");
        }


        Assert.assertEquals("BrowserStack Test 06 - Google Search", driver.getTitle());
    }
}
