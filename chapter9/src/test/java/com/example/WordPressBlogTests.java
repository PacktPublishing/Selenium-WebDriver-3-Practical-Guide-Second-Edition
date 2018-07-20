package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class WordPressBlogTests {

    WebDriver driver;

    @BeforeMethod
    public void setup() {

        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://demo-blog.seleniumacademy.com/wp/wp-admin");
    }

    @Test
    public void testAddNewPost() {
        WebElement email = driver.findElement(By.id("user_login"));
        WebElement pwd = driver.findElement(By.id("user_pass"));
        WebElement submit = driver.findElement(By.id("wp-submit"));
        email.sendKeys("admin");
        pwd.sendKeys("$$SUU3$$N#");
        submit.click();

        // Go to AllPosts page
        driver.get("http://demo-blog.seleniumacademy.com/wp/wp-admin/edit.php");

        // Add New Post
        WebElement addNewPost = driver.findElement(By.linkText("Add New"));
        addNewPost.click();

        // Add New Post's Content
        WebElement title = driver.findElement(By.id("title"));
        title.click();
        title.sendKeys("My First Post");

        driver.switchTo().frame("content_ifr");
        WebElement postBody = driver.findElement(By.id("tinymce"));
        postBody.sendKeys("This is description");
        driver.switchTo().defaultContent();

        // Publish the Post
        WebElement publish = driver.findElement(By.id("publish"));
        publish.click();
    }

    @Test
    public void testDeleteAPost() {
        WebElement email = driver.findElement(By.id("user_login"));
        WebElement pwd = driver.findElement(By.id("user_pass"));
        WebElement submit = driver.findElement(By.id("wp-submit"));
        email.sendKeys("admin");
        pwd.sendKeys("$$SUU3$$N#");
        submit.click();

        // Go to AllPosts page
        driver.get("http://demo-blog.seleniumacademy.com/wp/wp-admin/edit.php");

        // Click on the post to be deleted
        WebElement post = driver.findElement(By.linkText("My First Post"));
        post.click();

        // Delete Post
        WebElement publish = driver.findElement(By.linkText("Move to Trash"));
        publish.click();
    }

    @Test
    public void testPostCount() {
        WebElement email = driver.findElement(By.id("user_login"));
        WebElement pwd = driver.findElement(By.id("user_pass"));
        WebElement submit = driver.findElement(By.id("wp-submit"));
        email.sendKeys("admin");
        pwd.sendKeys("$$SUU3$$N#");
        submit.click();

        // Count the number of posts
        driver.get("http://demo-blog.seleniumacademy.com/wp/wp-admin/edit.php");
        WebElement postsContainer = driver.findElement(By.id("the-list"));
        List postsList = postsContainer.findElements(By.
                tagName("tr"));

        Assert.assertEquals(postsList.size(), 1);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}