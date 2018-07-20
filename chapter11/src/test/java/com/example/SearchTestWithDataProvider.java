package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author upgundecha
 */
public class SearchTestWithDataProvider {

    WebDriver driver;

    @DataProvider(name = "searchWords")
    public Object[][] provider() {
        return new Object[][]{
                {"phones", 3},
                {"music", 5},
                {"iphone 5s", 0}
        };
    }

    @BeforeMethod
    public void setup() {

        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://demo-store.seleniumacademy.com/");

    }

    @Test(dataProvider = "searchWords")
    public void searchProduct(String searchWord, int items) {

        // find search box and enter search string
        WebElement searchBox = driver.findElement(By.name("q"));

        searchBox.sendKeys(searchWord);

        WebElement searchButton =
                driver.findElement(By.className("search-button"));

        searchButton.click();

        assertThat(driver.getTitle())
                .isEqualTo("Search results for: '" + searchWord + "'");

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