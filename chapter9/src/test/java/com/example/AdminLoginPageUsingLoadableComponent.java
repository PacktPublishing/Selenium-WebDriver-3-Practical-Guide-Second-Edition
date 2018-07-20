package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class AdminLoginPageUsingLoadableComponent extends LoadableComponent<AdminLoginPageUsingLoadableComponent> {

    WebDriver driver;

    @FindBy(id = "user_login")
    WebElement email;

    @FindBy(id = "user_pass")
    WebElement password;

    @FindBy(id = "wp-submit")
    WebElement submit;

    public AdminLoginPageUsingLoadableComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AllPostsPage login(String username, String pwd) {
        email.sendKeys(username);
        password.sendKeys(pwd);
        submit.click();
        return PageFactory.initElements(driver, AllPostsPage.class);
    }

    @Override
    protected void load() {
        driver.get("http://demo-blog.seleniumacademy.com/wp/wp-admin");
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertTrue(driver.getCurrentUrl().contains("wp-admin"));
    }
}