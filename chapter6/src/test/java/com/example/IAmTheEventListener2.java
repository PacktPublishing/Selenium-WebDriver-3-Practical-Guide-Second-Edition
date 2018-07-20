package com.example;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class IAmTheEventListener2 extends AbstractWebDriverEventListener {

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        System.out.println("Before Navigate To "+ url);
    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {
        System.out.println("Before Navigate Back. Right now I'm at "
                + driver.getCurrentUrl());
    }

    @Override
    public void afterNavigateTo(String to, WebDriver driver) {
        try {

            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            URL url = new URL("https://raw.githubusercontent.com/GoogleChrome/" +
                    "accessibility-developer-tools/stable/dist/js/axs_testing.js");
            String script = IOUtils.toString(url.openStream(), StandardCharsets.UTF_8);
            jsExecutor.executeScript(script);
            String report = (String) jsExecutor.executeScript("var results = axs.Audit.run();" +
                    "return axs.Audit.createReport(results);");
            System.out.println("### Accessibility Report for " +  driver.getTitle() + "####");
            System.out.println(report);
            System.out.println("### END ####");

            // Get the Load Event End
            long loadEventEnd = (Long) jsExecutor.executeScript("return window.performance.timing.loadEventEnd;");
            // Get the Navigation Event Start
            long navigationStart = (Long) jsExecutor.executeScript("return window.performance.timing.navigationStart;");
            // Difference between Load Event End and Navigation Event Start is // Page Load Time
            System.out.println("Page Load Time is " + (loadEventEnd - navigationStart)/1000 + " seconds.");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
