package factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;

public class ChromeSettings {

  public ChromeOptions settings(String testName) {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("start-maximized");
    Map<String, Object> selenoidOptions = new HashMap<>();
    selenoidOptions.put("enableVNC", true);
    chromeOptions.setCapability("selenoid:options", selenoidOptions);
    return chromeOptions;
  }

}
