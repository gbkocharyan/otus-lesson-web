package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Waiters {

  private final WebDriverWait webDriverWait;
  private static final int TIMEOUT = Integer.parseInt(System.getProperty("timeout", "20"));

  public Waiters(WebDriver driver) {
    webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
  }

  public void waitForElementToBeClickable(WebElement element) {
    webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
  }

  public void waitForElementToBeVisible(WebElement element) {
    webDriverWait.until(ExpectedConditions.visibilityOf(element));
  }
}
