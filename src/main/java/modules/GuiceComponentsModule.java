package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.openqa.selenium.WebDriver;

public class GuiceComponentsModule extends AbstractModule {
  private WebDriver driver;

  public GuiceComponentsModule(WebDriver driver) {
    this.driver = driver;
  }


}
