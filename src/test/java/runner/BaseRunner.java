package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber/cucumber-html-report",
                "json:target/cucumber/cucumber-json-report.json"
        },
        dryRun = false,
        monochrome = true,
        features = "src/test/resources/features",
        glue = { "stepdefs" }
)
@RunWith(Cucumber.class)
public class BaseRunner {


    @BeforeClass()
    public static void runTomcat() throws Exception {
    }
}