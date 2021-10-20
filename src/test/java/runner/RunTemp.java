package runner;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = "classpath:features",
        glue = "stepdef",
        tags = "@Ui,@Api",
        plugin = {
                "pretty",
                "html:target/html/",
                "json:target/json/file.json",
        },
        strict = false,
        dryRun = false
)
public class RunTemp {
}
