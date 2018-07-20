package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeletePostPage {

    WebDriver driver;

    @FindBy(linkText = "Move to Trash")
    WebElement moveToTrash;

    public DeletePostPage(WebDriver driver) {
        this.driver = driver;
        System.out.println(driver.getCurrentUrl());
    }

    public void delete() {
        moveToTrash.click();
    }
}
