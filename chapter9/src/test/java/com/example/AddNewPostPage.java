package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddNewPostPage {

    WebDriver driver;

    @FindBy(id = "content_ifr")
    WebElement newPostContentFrame;

    @FindBy(id = "tinymce")
    WebElement newPostContentBody;

    @FindBy(id = "title")
    WebElement newPostTitle;

    @FindBy(id = "publish")
    WebElement newPostPublish;

    public AddNewPostPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addNewPost(String title, String descContent) {
        newPostTitle.click();
        newPostTitle.sendKeys(title);
        driver.switchTo().frame(newPostContentFrame);
        newPostContentBody.sendKeys(descContent);
        driver.switchTo().defaultContent();
        newPostPublish.click();
    }
}