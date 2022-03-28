package com.gateKeeper.methods;


import com.gateKeeper.elementHelper.helper.ElementHelper;
import com.gateKeeper.elementHelper.helper.StoreHelper;
import com.gateKeeper.elementHelper.model.ElementInfo;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.gateKeeper.utilsProperties.UtilsProperty.getProperties;
import static org.junit.Assert.*;

public class Methods {

    public static WebDriver driver;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    String strValue;


    public Methods(WebDriver driver) {

        Methods.driver = driver;
    }


    public void waitByMilliSeconds(long milliseconds) {

        try {
            Thread.sleep(milliseconds);
            logger.info("' " + milliseconds + " '" + " => Waited until");
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }


    public void writeTextToElement(String text, String key) {

        findElement(key).sendKeys(text,Keys.ENTER);
        logger.info("' " + key + " ' " + " ' " + text + " '" + " => Text is written");
    }


    public void clickElement(String key) {
        findElement(key).click();
        logger.info("' " + key + " '" + " => Element clicked");
    }


    public void getText(String key) {
        strValue = findElement(key).getText();
        logger.info("' " + key + " ' " + " ' " + strValue + " '" + " => Print text element");
    }


    public void checkElementDisplayed(String action, String key) {
        if (action.equals("not")) {
            try {

                boolean isDisplayed = findElement(key).isDisplayed();
                logger.info("' " + key + " ' " + " ' " + isDisplayed + " '" + " => Is displayed element");
                assertFalse("' " + key + " ' " + " ' " + isDisplayed + " '" + " => Is displayed element", isDisplayed);
            } catch (TimeoutException e) {

                logger.info("' " + key + " '" + " => Searched element is not visible");
            }
        } else {
            try {

                boolean isDisplayed = findElement(key).isDisplayed();
                logger.info("' " + key + " ' " + " ' " + isDisplayed + " '" + " => Is displayed element");
            } catch (TimeoutException e) {

                fail("' " + key + " '" + " => Searched element is not visible");
            }
        }
    }


    public void checkElementCssEquals(String key, String css, String action, String expectedValue) {
        String getCssValue = findElement(key).getCssValue(css);
        logger.info("' " + key + " ' " + " ' " + getCssValue + " '" + " => Get css value");

        if (action.equals("not")) {

            assertNotEquals("Expected css and are the same with actual" + " Expected: " + expectedValue + " Actual: " + getCssValue, getCssValue, expectedValue);
        } else {

            assertEquals("Expected css and are not the same with actual" + " Expected: " + expectedValue + " Actual: " + getCssValue, getCssValue, expectedValue);
        }
    }


    public void checkURL(String action, String action2, String expectedURL) {

        String getCurrentUrl = driver.getCurrentUrl();
        logger.info("' " + expectedURL + " ' " + " ' " + getCurrentUrl + " '" + " =>  Existing url from expected url");

        if (action.equals("not")) {
            if (action2.equals("equals")) {

                assertNotEquals("Expected URL and are the same with actual" + " Expected: " + expectedURL + " Actual: " + getCurrentUrl, getCurrentUrl, expectedURL);
            } else {

                assertFalse("Expected URL and are the same with actual" + " Expected: " + expectedURL + " Actual: " + getCurrentUrl, getCurrentUrl.contains(expectedURL));
            }
        } else {
            if (action2.equals("equals")) {

                assertEquals("Expected URL and are not the same with actual" + " Expected: " + expectedURL + " Actual: " + getCurrentUrl, getCurrentUrl, expectedURL);
            } else {

                assertTrue("Expected URL and are not the same with actual" + " Expected: " + expectedURL + " Actual: " + getCurrentUrl, getCurrentUrl.contains(expectedURL));
            }
        }
    }


    private WebElement findElement(String key) {
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By infoParam = ElementHelper.getElementInfoToBy(elementInfo);

        WebDriverWait webDriverWait = new WebDriverWait(driver, 30, 300);
        WebElement webElement = webDriverWait
                .until(ExpectedConditions.visibilityOfElementLocated(infoParam));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);

        return webElement;
    }

    public void checkGetText(String key, String action, String action2, String text) {
        String getText = findElement(key).getText();
        logger.info("' " + key + " ' " + " ' " + getText + " '" + " => Element text");

        if (text.equals("saveText")) {

            text = strValue;
            logger.info("' " + key + " ' " + " ' " + text + " '" + " => Element save text");
        }

        if (action.equals("not")) {
            if (action2.equals("equals")) {

                assertNotEquals("Expected text and are the same with actual" + " Expected: " + text + " Actual: " + getText, getText, text);
            } else {

                assertFalse("Expected text and are the same with actual" + " Expected: " + text + " Actual: " + getText, getText.contains(text));
            }
        } else {
            if (action2.equals("equals")) {

                assertEquals("Expected text and are not the same with actual" + " Expected: " + text + " Actual: " + getText, getText, text);
            } else {

                assertTrue("Expected text and are not the same with actual" + " Expected: " + text + " Actual: " + getText, getText.contains(text));
            }
        }

    }

    public void openUrl(String url) {
        driver.get(url);
        logger.info("' " + url + " '" + " => Url is gone");
    }

    public void scrollElement(String key) {
        WebElement webElement = findElement(key);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})", webElement);
        logger.info("' " + key + " '" + " => Element JS with scroll");
    }
}