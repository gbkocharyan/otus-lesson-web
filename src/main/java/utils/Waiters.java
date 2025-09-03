package utils;

import factory.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class Waiters {

  @Autowired
  private WebDriverFactory webDriverFactory;

  private WebDriverWait wait;

  private void ensureInitialized() {
    this.wait = new WebDriverWait(webDriverFactory.getDriver(), Duration.ofSeconds(20));
  }

  public void waitForElementToBeClickable(WebElement element) {
    wait.until(ExpectedConditions.elementToBeClickable(element));
  }

  public void waitForElementToBeVisible(WebElement element) {
    ensureInitialized();
    wait.until(ExpectedConditions.visibilityOf(element));
  }

  public void waitForElementToBeVisible(By element) {
    ensureInitialized();
    wait.until(ExpectedConditions.presenceOfElementLocated(element));
  }

  public void waitForPageLoad() {
    ensureInitialized();
    wait.until(driver -> ((JavascriptExecutor) driver)
        .executeScript("return document.readyState").equals("complete"));

    ExpectedCondition<Boolean> expectation = driver -> {
      assert driver != null;
      return ((JavascriptExecutor) driver).executeScript("return jQuery.active").toString()
          .equals("0");
    };
    try {
      Thread.sleep(1000);
      wait.until(expectation);
    } catch (Exception e) {
      //ignore
    }
  }
}
