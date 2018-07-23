package com.example;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class FirefoxSettingPreferences {
    public static void main(String... args) {

        System.setProperty("webdriver.gecko.driver",
                "./src/test/resources/drivers/geckodriver 2");

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("general.useragent.override",
                "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) " +
                        "AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 " +
                        " Mobile/15A356 Safari/604.1");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile);
        FirefoxDriver driver = new FirefoxDriver(firefoxOptions);
        driver.get("http://facebook.com");
    }
}
