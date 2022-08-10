package test.trelloapiwithcucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","html:target/cucumber-report.html",
                "json:target/cucumber.json",
                "junit:target/cucumber-results.xml"},//test report
        features = {"src/test/resources"},//feature file location
        tags ="@ApiTest")


public class CucumberTestRunner {
}
