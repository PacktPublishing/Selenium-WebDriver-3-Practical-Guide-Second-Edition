package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author upgundecha
 */
public class SearchTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {

        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://demo-store.seleniumacademy.com/");

    }

    @Parameters({"searchWord", "items"})
    @Test
    public void searchProduct(String searchWord, int items) {

        // find search box and enter search string
        WebElement searchBox = driver.findElement(By.name("q"));

        searchBox.sendKeys(searchWord);

        WebElement searchButton =
                driver.findElement(By.className("search-button"));

        searchButton.click();

        assertThat(driver.getTitle())
                .isEqualTo("Search results for: '" + searchWord  + "'");

        List<WebElement> searchItems = driver
                .findElements(By.xpath("//h2[@class='product-name']/a"));

        assertThat(searchItems.size())
                .isEqualTo(items);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}