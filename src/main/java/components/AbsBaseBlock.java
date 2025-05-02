package components;

import annotations.Component;
import common.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbsBaseBlock extends AbsCommon {

  public AbsBaseBlock(WebDriver driver) {
    super(driver);
  }

  public void waitForComponentVisibility() {
    try {
      waiters.waitForElementToBeVisible(findComponentElement());
    } catch (Exception e) {
      throw new RuntimeException("Failed to wait for the component to be visible: " + e.getMessage(), e);
    }
  }

  private WebElement findComponentElement() {
    By locator = fetchComponentLocator();
    return driver.findElement(locator);
  }

  private By fetchComponentLocator() {
    String componentLocator = annotationUtils
        .getAnnotationInstance(this.getClass(), Component.class)
        .value();
    String[] locatorParts = componentLocator.split(":");
    String locatorType = locatorParts[0];
    String locatorValue = locatorParts[1];
    return switch (locatorType) {
      case "css" -> By.cssSelector(locatorValue);
      case "xpath" -> By.xpath(locatorValue);
      case "id" -> By.id(locatorValue);
      default -> throw new IllegalArgumentException("Unsupported locator type: " + locatorType);
    };
  }

}
