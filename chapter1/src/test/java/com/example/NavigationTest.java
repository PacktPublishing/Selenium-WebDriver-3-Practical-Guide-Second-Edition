package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;


/**
 * @author upgundecha
 */
public class NavigationTest {

    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {

        // set path of Chromedriver executable
        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver");

        // initialize new WebDriver session
        driver = new ChromeDriver();
    }

    @Test
    public void navigateToAUrl() {

        // navigate to the web site
        driver.get("http://demo-store.seleniumacademy.com/");

        // Validate page title
        Assert.assertEquals(driver.getTitle(), "Madison Island");
    }

    @AfterMethod
    public void afterMethod() {

        // close and quit the browser
        driver.quit();
    }

}
