package support;

import factory.WebDriverFactory;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;
import java.util.HashMap;
import java.util.Map;

@ScenarioScoped
public class GuiceScoped {

  private WebDriver driver = new WebDriverFactory().create();

  public WebDriver getDriver() {
    return this.driver;
  }

  private final Map<String, Object> storeObject = new HashMap<>();

  public <T> void store(T object, String key) {
    storeObject.put(key, object);
  }

  public <T> T retrieve(String key) {
    return safelyCast(storeObject.get(key));
  }

  @SuppressWarnings("unchecked")
  private <T> T safelyCast(Object obj) {
    return (T) obj;
  }
}
