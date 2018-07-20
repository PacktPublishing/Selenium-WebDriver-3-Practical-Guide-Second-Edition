package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class ActionsTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException {

        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void shouldPerformCompositeAction() {

        driver.get("http://guidebook.seleniumacademy.com/Selectable.html");

        WebElement one = driver.findElement(By.name("one"));
        WebElement three = driver.findElement(By.name("three"));
        WebElement five = driver.findElement(By.name("five"));

        // Add all the actions into the Actions actions.
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL)
                .click(one)
                .click(three)
                .click(five)
                .keyUp(Keys.CONTROL);

        // Generate the composite action.
        Action compositeAction = actions.build();

        // Perform the composite action.
        compositeAction.perform();
    }

    @Test
    public void shouldPerformAction() {

        driver.get("http://guidebook.seleniumacademy.com/Selectable.html");

        WebElement one = driver.findElement(By.name("one"));
        WebElement three = driver.findElement(By.name("three"));
        WebElement five = driver.findElement(By.name("five"));

        // Add all the actions into the Actions actions.
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL)
                .click(one)
                .click(three)
                .click(five)
                .keyUp(Keys.CONTROL);

        // Perform the action
        actions.perform();
    }

    @Test
    public void shouldMoveByOffSet() {

        driver.get("http://guidebook.seleniumacademy.com/Selectable.html");

        WebElement three = driver.findElement(By.name("three"));
        System.out.println("X coordinate: " + three.getLocation().getX()
                + ", Y coordinate: " + three.getLocation().getY());
        Actions actions = new Actions(driver);
        actions.moveByOffset(three.getLocation().getX() + 1, three.
                getLocation().getY() + 1);
        actions.perform();
    }

    @Test
    public void shouldMoveByOffSetAndClick() {

        driver.get("http://guidebook.seleniumacademy.com/Selectable.html");

        WebElement seven = driver.findElement(By.name("seven"));
        System.out.println("X coordinate: " + seven.getLocation().getX() +
                ", Y coordinate: " + seven.getLocation().getY());
        Actions actions = new Actions(driver);
        actions.moveByOffset(seven.getLocation().getX() + 1, seven.
                getLocation().getY() + 1).click();
        actions.perform();
    }

    @Test
    public void shouldMoveByOffSetAndClickMultiple() {

        driver.get("http://guidebook.seleniumacademy.com/Selectable.html");

        WebElement one = driver.findElement(By.name("one"));
        WebElement eleven = driver.findElement(By.name("eleven"));
        WebElement five = driver.findElement(By.name("five"));
        int border = 1;
        int tileWidth = 100;
        int tileHeight = 80;
        Actions actions = new Actions(driver);

        //Click on One
        actions.moveByOffset(one.getLocation().getX() + border, one.
                getLocation().getY() + border).click();
        actions.build().perform();

        // Click on Eleven
        actions.moveByOffset(2 * tileWidth + 4 * border, 2 * tileHeight + 4 * border).
                click();
        actions.build().perform();

        //Click on Five
        actions.moveByOffset(-2 * tileWidth - 4 * border, -tileHeight - 2 * border).
                click();
        actions.build().perform();
    }

    @Test
    public void shouldClickOnElement() {

        driver.get("http://guidebook.seleniumacademy.com/Selectable.html");

        WebElement one = driver.findElement(By.name("one"));
        WebElement eleven = driver.findElement(By.name("eleven"));
        WebElement five = driver.findElement(By.name("five"));
        Actions actions = new Actions(driver);

//        //Click on One
//        actions.click(one);
//        actions.build().perform();
//
//        // Click on Eleven
//        actions.click(eleven);
//        actions.build().perform();
//
//        //Click on Five
//        actions.click(five);
//        actions.build().perform();

        actions.click(one)
                .click(eleven)
                .click(five)
                .build().perform();
    }

    @Test
    public void shouldClickAndHold() {

        driver.get("http://guidebook.seleniumacademy.com/Sortable.html");

        Actions actions = new Actions(driver);

        //Move tile3 to the position of tile2
        actions.moveByOffset(200, 20)
                .clickAndHold()
                .moveByOffset(120, 0)
                .perform();
    }

    @Test
    public void shouldClickAndHoldElement() {

        driver.get("http://guidebook.seleniumacademy.com/Sortable.html");

        Actions actions = new Actions(driver);
        WebElement three = driver.findElement(By.name("three"));

        //Move tile3 to the position of tile2
        actions.clickAndHold(three)
                .moveByOffset(120, 0)
                .perform();
    }

    @Test
    public void shouldClickAndHoldAndRelease() {

        driver.get("http://guidebook.seleniumacademy.com/Sortable.html");

        WebElement three = driver.findElement(By.name("three"));
        Actions actions = new Actions(driver);

        //Move tile3 to the position of tile2
        actions.clickAndHold(three)
                .moveByOffset(120, 0)
                .release()
                .perform();
    }

    @Test
    public void shouldClickAndHoldAndReleaseOnElement() {

        driver.get("http://guidebook.seleniumacademy.com/Sortable.html");

        WebElement three = driver.findElement(By.name("three"));
        WebElement two = driver.findElement(By.name("two"));
        Actions actions = new Actions(driver);

        //Move tile3 to the position of tile2
        actions.clickAndHold(three)
                .release(two)
                .perform();
    }

    @Test
    public void shouldClickAndHoldAndMove() {

        driver.get("http://guidebook.seleniumacademy.com/Sortable.html");

        WebElement three = driver.findElement(By.name("three"));
        Actions actions = new Actions(driver);

        //Move tile3 to the position of tile2
        actions.moveToElement(three)
                .clickAndHold()
                .moveByOffset(120, 0)
                .perform();
    }

    @Test
    public void shouldDrag() {

        driver.get("http://guidebook.seleniumacademy.com/DragMe.html");

        WebElement dragMe = driver.findElement(By.id("draggable"));
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(dragMe, 300, 200).perform();
    }

    @Test
    public void shouldDragAndDrop() {

        driver.get("http://guidebook.seleniumacademy.com/DragAndDrop.html");

        WebElement src = driver.findElement(By.id("draggable"));
        WebElement trgt = driver.findElement(By.id("droppable"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(src, trgt).perform();
    }

    @Test
    public void shouldDoubleClick() {

        driver.get("http://guidebook.seleniumacademy.com/DoubleClick.html");

        WebElement dblClick= driver.findElement(By.name("dblClick"));
        Actions actions = new Actions(driver);
        actions.moveToElement(dblClick).doubleClick().perform();
    }

    @Test
    public void shouldDoubleClickElement() {

        driver.get("http://guidebook.seleniumacademy.com/DoubleClick.html");

        WebElement dblClick = driver.findElement(By.name("dblClick"));
        Actions actions = new Actions(driver);
        actions.doubleClick(dblClick).perform();
    }

    @Test
    public void shouldContextClick() {

        driver.get("http://guidebook.seleniumacademy.com/ContextClick.html");

        WebElement contextMenu = driver.findElement(By.id("div-context"));
        Actions actions = new Actions(driver);
        actions.contextClick(contextMenu)
                .click(driver.findElement(By.name("Item 4")))
                .perform();
    }

    @Test
    public void shouldContextClickAtCurrentLocation() {

        driver.get("http://guidebook.seleniumacademy.com/ContextClick.html");

        WebElement contextMenu = driver.findElement(By.id("div-context"));
        Actions actions = new Actions(driver);
        actions.moveToElement(contextMenu)
                .contextClick()
                .click(driver.findElement(By.name("Item 4")))
                .perform();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

