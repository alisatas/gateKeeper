package com.gateKeeper.hook;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features"},
        glue = {"com.gateKeeper"},
        plugin = {
                "json:target/cucumber-html-reports/json/cucumber.json"
        },
        monochrome = true
)

public class CucumberRunner {

}