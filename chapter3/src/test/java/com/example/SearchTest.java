package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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


        List<WebElement> searchItems = driver
                .findElements(By.cssSelector("h2.product-name a"));

//        List<String> products = new ArrayList<>();
//
//        for(WebElement item : searchItems) {
//            products.add(item.getText());
//        }
//
//        System.out.println(products);

        List<String> products = searchItems
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        System.out.println(products);

        List<String> languages = new ArrayList<String>();
        languages.add("English");
        languages.add("German");
        languages.add("French");

        for(String language : languages) {
            System.out.println(language);
        }

        languages.stream().forEach(System.out::println);

        languages.stream().map(item -> item.toUpperCase());

        languages.stream().sorted();

        List<String> sortedLanguages = languages.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(sortedLanguages);

        List<Product> searchResult = new ArrayList<>();
        searchResult.add(new Product("MADISON OVEREAR HEADPHONES", 125.00));
        searchResult.add(new Product("MADISON EARBUDS", 35.00));
        searchResult.add(new Product("MP3 PLAYER WITH AUDIO", 185.00));

        Product product = searchResult.stream()
                .min(Comparator.comparing(item -> item.getPrice()))
                .get();

        System.out.println("The product with lowest price is " + product.getName());

        product = searchResult.stream()
                .max(Comparator.comparing(item -> item.getPrice()))
                .get();

        System.out.println("The product with highest price is " + product.getName());

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    class Product {
        String name;
        Double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public Double getPrice() {
            return price;
        }

    }
}



