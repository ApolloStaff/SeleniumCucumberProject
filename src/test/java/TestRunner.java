import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/java/Login",
        glue = {"StepDefinitions"},
        monochrome = true,
        plugin = {"pretty", "html:target/HtmlReports"}
)
public class TestRunner {
}
