package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage {

    WebDriver driver;

    @FindBy(id = "content_ifr")
    WebElement newPostContentFrame;

    @FindBy(id = "tinymce")
    WebElement newPostContentBody;

    @FindBy(id = "title")
    WebElement newPostTitle;

    @FindBy(id = "publish")
    WebElement newPostPublish;

    public EditPostPage(WebDriver driver) {
        this.driver = driver;
        System.out.println(driver.getCurrentUrl());
    }

    public void editPost(String title, String descContent) {
        newPostTitle.click();
        newPostTitle.clear();
        newPostTitle.sendKeys(title);
        driver.switchTo().frame(newPostContentFrame);
        newPostContentBody.clear();
        newPostContentBody.sendKeys(descContent);
        driver.switchTo().defaultContent();
        newPostPublish.click();
    }
}