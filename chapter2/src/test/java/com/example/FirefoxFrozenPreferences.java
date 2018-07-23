package com.example;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class FirefoxFrozenPreferences {
    public static void main(String... args) {

        System.setProperty("webdriver.gecko.driver",
                "./src/test/resources/drivers/geckodriver 2");

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.shell.checkDefaultBrowser", true);
        profile.setAssumeUntrustedCertificateIssuer(false);
        profile.setAcceptUntrustedCertificates(false);

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile);

        FirefoxDriver driver = new FirefoxDriver(firefoxOptions);
        driver.get("http://facebook.com");
    }
}


