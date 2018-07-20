package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminLoginPage {

    WebDriver driver;

    @FindBy(id = "user_login")
    WebElement email;

    @FindBy(id = "user_pass")
    WebElement password;

    @FindBy(id = "wp-submit")
    WebElement submit;

    public AdminLoginPage(WebDriver driver) {
        this.driver = driver;
        driver.get("http://demo-blog.seleniumacademy.com/wp/wp-admin");
    }

    public AllPostsPage login(String username, String pwd) {
        email.sendKeys(username);
        password.sendKeys(pwd);
        submit.click();
        return PageFactory.initElements(driver,
                AllPostsPage.class);
    }
}