package extensions;

import com.google.inject.Guice;
import com.google.inject.Injector;
import factory.WebDriverFactory;
import modules.GuiceComponentsModule;
import modules.GuicePagesModule;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

public class UIExtension implements BeforeEachCallback, AfterEachCallback {

  private Injector injector;

  @Override
  public void afterEach(ExtensionContext context) {
    WebDriver driver = injector.getInstance(WebDriver.class);
    injector.getInstance(MainPage.class).open();
    if (driver != null) {
      driver.quit();
    }
  }

  @Override
  public void beforeEach(ExtensionContext context) {
    WebDriver driver = new WebDriverFactory().create();
    injector = Guice.createInjector(new GuicePagesModule(driver), new GuiceComponentsModule(driver));
    context.getTestInstance().ifPresent(injector::injectMembers);
  }
}
