package com.example;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author upgundecha
 */
public class SearchAndNavigationTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException {

        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver");

        driver = new ChromeDriver();
        driver.navigate().to("http://demo-store.seleniumacademy.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./target/screenshot.png"));

    }

    @Test
    public void searchProduct() {

        // find search box and enter search string
        WebElement searchBox = driver.findElement(By.name("q"));

        searchBox.sendKeys("Phones");

        WebElement searchButton =
                driver.findElement(By.className("search-button"));

        searchButton.click();

        assertThat(driver.getTitle())
                .isEqualTo("Search results for: 'Phones'");

        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

