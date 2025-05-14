package otus.steps.common;

import com.google.inject.Inject;
import io.cucumber.java.ru.Дано;
import support.GuiceScoped;

public class CommonSteps {

  private final String baseUrl = System.getProperty("baseUrl");

  @Inject
  GuiceScoped guiceScoped;

  @Дано("Открыт браузер Chrome")
  public void openBrowser() {
    guiceScoped.getDriver().get(baseUrl);
  }
}
