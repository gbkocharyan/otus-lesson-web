package utils;

import factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionUtils {

  @Autowired
  private WebDriverFactory webDriverFactory;

  private Actions actions;

  private void ensureInitialized() {
    if (this.actions == null) {
      try {
        this.actions = new Actions(webDriverFactory.getDriver());
      } catch (IllegalStateException e) {
        // Create the driver if it doesn't exist yet
      }
    }
  }

  public void moveToElement(WebElement element) {
    actions.moveToElement(element).build().perform();
  }
}
