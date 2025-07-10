package factory;

import exceptions.BrowserNotSupportedException;
import factory.settings.ChromeSettings;
import listeners.MouseListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory {
  private final String browserName = System.getProperty("browser");
  private final String remoteIp = System.getProperty("remoteIp");

  public WebDriver create() {
    switch (browserName) {
      case "chrome":
        ChromeOptions options = new ChromeSettings().settings();
        try {
          if (remoteIp != null && !remoteIp.isEmpty()) {
            // Remote WebDriver (for Selenoid)
            String remoteUrl = "http://" + remoteIp + "/wd/hub";
            return new EventFiringDecorator<>(new MouseListener()).decorate(
                new RemoteWebDriver(new URL(remoteUrl), options));
          } else {
            // Local WebDriver
            return new EventFiringDecorator<>(new MouseListener()).decorate(
                new ChromeDriver(options));
          }
        } catch (MalformedURLException e) {
          throw new RuntimeException("Invalid Selenoid URL", e);
        }
    }
    throw new BrowserNotSupportedException(browserName);
  }
}
