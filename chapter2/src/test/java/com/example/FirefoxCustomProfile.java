package com.example;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.io.IOException;

public class FirefoxCustomProfile {
    public static void main(String... args) throws IOException {

        System.setProperty("webdriver.gecko.driver",
                "./src/test/resources/drivers/geckodriver 2");

        FirefoxProfile profile = new FirefoxProfile();
        profile.addExtension(
                new File("./src/test/resources/extensions/xpath_finder.xpi"));

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile);

        FirefoxDriver driver = new FirefoxDriver(firefoxOptions);

        try {
            driver.get("http://www.google.com");
        } finally {
            driver.quit();
        }

    }
}
