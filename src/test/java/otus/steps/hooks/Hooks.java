package otus.steps.hooks;

import com.google.inject.Inject;
import io.cucumber.java.After;
import support.GuiceScoped;

public class Hooks {

  @Inject
  GuiceScoped guiceScoped;

  @After
  public void afterScenario() {
    if(guiceScoped.getDriver() != null) {
      guiceScoped.getDriver().quit();
    }
  }
}
