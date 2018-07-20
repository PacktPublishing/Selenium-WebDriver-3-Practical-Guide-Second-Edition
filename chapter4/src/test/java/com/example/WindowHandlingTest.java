package com.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class WindowHandlingTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException {

        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver");

        driver = new ChromeDriver();
        driver.get("http://guidebook.seleniumacademy.com/Window.html");

    }

    @Test
    public void handleWindow() {

        String firstWindow = driver.getWindowHandle();
        System.out.println("First Window Handle is: " + firstWindow);

        WebElement link = driver.findElement(By.linkText("Google Search"));
        link.click();

        String secondWindow = driver.getWindowHandle();
        System.out.println("Second Window Handle is: " + secondWindow);
        System.out.println("Number of Window Handles so for: "
                + driver.getWindowHandles().size());

        driver.switchTo().window(firstWindow);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
