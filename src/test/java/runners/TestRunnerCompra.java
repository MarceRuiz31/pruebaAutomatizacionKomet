package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/features/CheckoutOrdenCompra.feature"
        , glue = {"proyecto/automatizacion/komet/test/stepdefinition/"}
        ,plugin = {"pretty", "html:target/cucumber", "json:target/TestRunnerCompra.json"}
)
public class TestRunnerCompra {
}
