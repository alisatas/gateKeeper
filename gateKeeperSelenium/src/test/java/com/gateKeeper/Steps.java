package com.gateKeeper;


import com.gateKeeper.hook.Hooks;
import com.gateKeeper.methods.Methods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class Steps {

    public static WebDriver driver;
    final Methods methods;


    public Steps() {

        driver = Hooks.getDriver();
        methods = new Methods(driver);
    }


    @Given("^\"([^\"]*)\" wait milliseconds$")
    public void waitByMilliSeconds(long milliseconds) {

        methods.waitByMilliSeconds(milliseconds);
    }


    @Given("^Write value \"([^\"]*)\" to element \"([^\"]*)\"$")
    public void writeTextToElement(String text, String key) {

        methods.writeTextToElement(text, key);
    }


    @Given("^Click on the \"([^\"]*)\" element$")
    public void clickElement(String key) {

        methods.clickElement(key);

    }


    @Given("^Print text value of element \"([^\"]*)\" and save$")
    public void getText(String key) {

        methods.getText(key);
    }


    @Then("^I expect is *(|not) displayed check element \"([^\"]*)\"$")
    public void checkElementDisplayed(String action, String key) {

        methods.checkElementDisplayed(action, key);
    }


    @Then("^I expect check if \"([^\"]*)\" element's css \"([^\"]*)\" *(|not) equals to the value \"([^\"]*)\"$")
    public void checkElementCssEquals(String key, String css, String action, String expectedValue) {

        methods.checkElementCssEquals(key, css, action, expectedValue);
    }


    @Then("^I expect element \"([^\"]*)\" *(|not) (contains|equals) text \"([^\"]*)\"$")
    public void checkGetText(String key, String action, String action2, String text) {

        methods.checkGetText(key, action, action2, text);
    }

    @Then("I expect the page to contains url {string}")
    public void iExpectThePageToContainsUrl(String action, String action2, String expectedURL) {
        methods.checkURL(action,action2,expectedURL);
    }

    @Given("^Open url \"([^\"]*)\"$")
    public void openUrl(String url) {
        methods.openUrl(url);
    }

    @Given("^Scroll element \"([^\"]*)\"$")
    public void scrollElement(String key) {
        methods.scrollElement(key);
    }
}