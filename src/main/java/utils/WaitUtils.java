package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WaitUtils {

  private final WebDriverWait webDriverWait;

  public WaitUtils(WebDriver driver) {
    webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  public void waitForElementToBeClickable(WebElement element) {
    webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
  }

  public void waitForElementToBeVisible(WebElement element) {
    webDriverWait.until(ExpectedConditions.visibilityOf(element));
  }
}
