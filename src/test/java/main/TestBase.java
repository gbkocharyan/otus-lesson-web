package main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import factory.WebDriverFactory;
import modules.GuiceComponentsModule;
import modules.GuicePagesModule;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

  private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

  @AfterMethod
  public void afterEach() {
    WebDriver webDriver = DRIVER.get();
    if (webDriver != null) {
      webDriver.quit();
      DRIVER.remove();
    }
  }

  @BeforeMethod
  public void beforeEach() {
    WebDriver webDriver = new WebDriverFactory().create();
    DRIVER.set(webDriver);
    Injector injector = Guice.createInjector(new GuicePagesModule(webDriver), new GuiceComponentsModule(webDriver));
    injector.injectMembers(this);
  }
}
