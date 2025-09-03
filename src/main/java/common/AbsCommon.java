package common;

import factory.WebDriverFactory;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import utils.ActionUtils;
import utils.AnnotationUtils;
import utils.Waiters;

public abstract class AbsCommon {

  @Autowired
  protected WebDriverFactory webDriverFactory;

  @Autowired
  protected Waiters waiters;

  @Autowired
  protected ActionUtils actionUtils;

  @PostConstruct
  public void initPages() {
    PageFactory.initElements(webDriverFactory.getDriver(), this);
  }

  protected void click(WebElement element) {
    waiters.waitForElementToBeClickable(element);
    element.click();
  }

  protected String getText(WebElement element) {
    waiters.waitForElementToBeVisible(element);
    return element.getText();
  }

  protected String getElementAttribute(WebElement element) {
    return element.getDomAttribute("value");
  }

  public void addCookie() {
    JavascriptExecutor js = (JavascriptExecutor) webDriverFactory.getDriver();
    js.executeScript("localStorage.setItem('cookieAccess', 'true');");
  }

}
