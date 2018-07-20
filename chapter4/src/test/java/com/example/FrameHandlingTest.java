package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class FrameHandlingTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException {

        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver");

        driver = new ChromeDriver();
        driver.get("http://guidebook.seleniumacademy.com/Frames.html");
    }

    @Test
    public void switchBetweenFrames() {

        // First Frame
        driver.switchTo().frame(0);
        WebElement firstField = driver.findElement(By.name("1"));
        firstField.sendKeys("I'm Frame One");
        driver.switchTo().defaultContent();

        // Second Frame
        driver.switchTo().frame(1);
        WebElement secondField = driver.findElement(By.name("2"));
        secondField.sendKeys("I'm Frame Two");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
