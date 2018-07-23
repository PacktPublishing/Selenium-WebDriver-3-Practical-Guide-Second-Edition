package com.example;


import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SearchOniOSTest {

    private WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {

        // Set the desired capabilities for iOS- iPhone X
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("platformVersion", "11.4");
        caps.setCapability("deviceName", "iPhone X");
        caps.setCapability("browserName", "safari");

        // Create an instance of AndroidDriver for testing on Android platform
        // connect to the local Appium server running on a different machine
        // We will use WebElement type for testing the Web application
        driver = new IOSDriver<>(new URL(
                "http://192.168.0.101:4723/wd/hub"), caps);
        driver.get("http://demo-store.seleniumacademy.com/");
    }

    @Test
    public void searchProduct() {

        WebElement lookingGlassIcon =
                driver.findElement(By
                        .cssSelector("a.skip-search span.icon"));

        lookingGlassIcon.click();

        // find search box and enter search string
        WebElement searchBox = driver.findElement(By.name("q"));

        searchBox.sendKeys("Phones");

        WebElement searchButton =
                driver.findElement(By.className("search-button"));

        searchButton.click();

        List<WebElement> searchItems = new WebDriverWait(driver, 30)
                .until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By
                                .cssSelector("h2.product-name a")));

        assertThat(searchItems.size())
                .isEqualTo(3);

    }


    @AfterTest
    public void tearDown() throws Exception {
        // Close the browser
        driver.quit();
    }
}

