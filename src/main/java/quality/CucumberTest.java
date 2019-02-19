package quality;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        glue = "stepdefs",
        features = {"src/main/resources"},
        tags="@goszacupka"
)

public class CucumberTest {
}