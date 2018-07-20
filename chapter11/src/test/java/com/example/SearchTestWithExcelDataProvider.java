package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author upgundecha
 */
public class SearchTestWithExcelDataProvider {

    WebDriver driver;

    @DataProvider(name = "searchWords")
    public Object[][] provider() throws Exception {
        SpreadsheetData spreadsheetData = new SpreadsheetData();
        return spreadsheetData.getCellData("./src/test/resources/data/data.xlsx");
    }

    @BeforeMethod
    public void setup() {

        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://demo-store.seleniumacademy.com/");

    }

    @Test(dataProvider = "searchWords")
    public void searchProduct(String searchWord, String items) {

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
                .isEqualTo(Integer.parseInt(items));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}