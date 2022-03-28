package com.gateKeeper.hook;


import com.gateKeeper.utilsProperties.UtilsProperty;
import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

import static com.gateKeeper.utilsProperties.UtilsProperty.getProperties;

public class Hooks {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    final UtilsProperty utilsProperty = new UtilsProperty();
    String str = utilsProperty.loadProperties();

    public static WebDriver driver;
    public String getBaseUrl = getProperties("website.url");
    public String selectBrowser = getProperties("web.driver.browser");


    @Before
    public void beforeTest(Scenario scenario) {

        logger.info("======================================================================" + "\r\n");
        logger.info("------------------------------ BROWSER -------------------------------" + "\r\n");
        logger.info("BROWSER: " + selectBrowser.toUpperCase());

        String getUri = String.valueOf(scenario.getUri());
        String[] strSplit = getUri.split("features/");
        String resultSplit = strSplit[1];

        logger.info("**********************************************************************" + "\r\n");
        logger.info("------------------------------- FEATURE ------------------------------" + "\r\n");
        logger.info("FEATURE NAME: " + resultSplit.toUpperCase());

        logger.info("______________________________________________________________________" + "\r\n");
        logger.info("------------------------------ SCENARIO ------------------------------" + "\r\n");
        logger.info("SCENARIO TAG NAME: " + scenario.getSourceTagNames());
        logger.info("SCENARIO NAME: " + scenario.getName());

        try {
            if (selectBrowser.equals("Chrome")) {
                WebDriverManager.chromedriver().browserVersion("98").setup();
                ChromeOptions options = new ChromeOptions();
                //options.addArguments("--headless");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-dev-shm-usage");
                options.setExperimentalOption("w3c", false);
                driver = new ChromeDriver(options);
            } else if (selectBrowser.equals("Firefox")) {

                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }

            //driver.manage().window().maximize();
            driver.get(getBaseUrl);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    @After
    public void afterTest(Scenario scenario) {

        if (scenario.isFailed()) {

            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png",
                    "------------------- ! TEST FAİL SCREENSHOT ! -------------------" + "\r\n");

            logger.info("--------------------------- ! TEST FAİL ! ---------------------------" + "\r\n");
        } else {

            logger.info("---------------------------- TEST PASSED -----------------------------" + "\r\n");
        }

        if (driver != null) {

            driver.quit();
            logger.info("---------------------------- DRİVER QUIT -----------------------------" + "\r\n");
            logger.info("======================================================================" + "\r\n");
        }
    }


    public static WebDriver getDriver() {

        return driver;
    }

}