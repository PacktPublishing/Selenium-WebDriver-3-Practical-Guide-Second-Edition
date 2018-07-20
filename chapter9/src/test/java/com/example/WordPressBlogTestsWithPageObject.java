package com.example;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WordPressBlogTestsWithPageObject {

    WebDriver driver;
    String username = "admin";
    String password = "$$SUU3$$N#";

    @BeforeMethod
    public void setup() {

        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void testAddNewPost() {
        AdminLoginPage loginPage =
                PageFactory.initElements(driver, AdminLoginPage.class);
        AllPostsPage allPostsPage = loginPage.login(username, password);
        allPostsPage.createANewPost("Creating New Post using PageObjects",
                "Its good to use PageObjects");
    }

    @Test
    public void testEditPost() {
        AdminLoginPage loginPage =
                PageFactory.initElements(driver, AdminLoginPage.class);
        AllPostsPage allPostsPage = loginPage.login(username, password);
        allPostsPage.editAPost("Creating New Post using PageObjects",
                "Editing Post using PageObjects", "Test framework low maintenance");
    }

    @Test (dependsOnMethods = "testEditPost")
    public void testDeletePost() {
        AdminLoginPage loginPage =
                PageFactory.initElements(driver, AdminLoginPage.class);
        AllPostsPage allPostsPage = loginPage.login(username, password);
        allPostsPage.deleteAPost("Editing Post using PageObjects");
    }

    @Test
    public void testCountPost() {
        AdminLoginPage loginPage =
                PageFactory.initElements(driver, AdminLoginPage.class);
        AllPostsPage allPostsPage = loginPage.login(username, password);
        Assert.assertEquals(allPostsPage.getAllPostsCount(), 1);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}