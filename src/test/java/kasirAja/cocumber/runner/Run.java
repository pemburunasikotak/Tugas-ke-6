package kasirAja.cocumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/kasirAja/cocumber/resources"},
        glue = "kasirAja.cocumber.stepDef",
        plugin = {"html:target/HTML_report.html", "json:target/JSON_report.json", "junit:target/XML_report.xml"},
        tags = "@TDD"

)
public class Run {

}