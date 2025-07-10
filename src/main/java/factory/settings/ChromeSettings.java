package factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;

public class ChromeSettings implements IBrowserSettings {

  @Override
  public ChromeOptions settings() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("start-maximized");
    Map<String, Object> selenoidOptions = new HashMap<>();
    selenoidOptions.put("enableVNC", true);
    chromeOptions.setCapability("selenoid:options", selenoidOptions);
    return chromeOptions;
  }
}
