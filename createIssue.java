package com.nortal.course.selenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by kristor on 3.02.2015.
 */


public class createIssue {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://testjira.webmedia.int/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testCreateissue() throws Exception {
        driver.get(baseUrl + "/login.jsp");
        driver.findElement(By.id("login-form-username")).clear();
        driver.findElement(By.id("login-form-username")).sendKeys("tstcreateissue7");
        driver.findElement(By.id("login-form-password")).clear();
        driver.findElement(By.id("login-form-password")).sendKeys("password");
        driver.findElement(By.id("login-form-submit")).click();
        driver.findElement(By.id("create_link")).click();
        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if (isElementPresent(By.id("summary"))) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }

        driver.findElement(By.id("summary")).clear();
        driver.findElement(By.id("summary")).sendKeys("Test issue Selenium IDE training 1");
        driver.findElement(By.id("priority-field")).click();
        assertTrue(isElementPresent(By.xpath("//a[contains(text(),'Blocker')]")));
        // ERROR: Caught exception [ERROR: Unsupported command [highlight | //a[contains(text(),'Blocker')] | ]]
        // ERROR: Caught exception [ERROR: Unsupported command [clickAt | //a[@class='aui-list-item-link imagebacked aui-iconised-link' and @title='Blocker'] | 10,10]]
        // ERROR: Caught exception [Error: unknown strategy [class] for locator [class=aui-list-item aui-list-item-li-blocker active]]
        driver.findElement(By.id("assign-to-me-trigger")).click();
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys("Very first task for IDE test");
        driver.findElement(By.id("timetracking_originalestimate")).clear();
        driver.findElement(By.id("timetracking_originalestimate")).sendKeys("1h");
        driver.findElement(By.id("customfield_17501-2")).click();
        driver.findElement(By.id("customfield_17501-1")).click();
        driver.findElement(By.id("customfield_17501-2")).click();
        driver.findElement(By.id("customfield_17502-1")).click();
        new Select(driver.findElement(By.id("resolution"))).selectByVisibleText("Unresolved");
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
