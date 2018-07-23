package com.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SearchTest {

    private WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {


        String USERNAME = "benehmke";
        String AUTOMATE_KEY = "RzA1hJpssWs1i4NcxRxR";
        String URL = "https://" + USERNAME + ":"
                + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

        // Set the desired capabilities for iPhone X
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "iPhone");
        caps.setCapability("device", "iPhone X");
        caps.setCapability("realMobile", "true");
        caps.setCapability("os_version", "11.0");

        driver = new RemoteWebDriver(new URL(URL), caps);

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

