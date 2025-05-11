package common;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import support.GuiceScoped;
import utils.ActionUtils;
import utils.AnnotationUtils;
import utils.Waiters;

public abstract class AbsCommon {

  protected WebDriver driver;
  protected AnnotationUtils annotationUtils;
  protected ActionUtils actionUtils;
  protected Waiters waiters;
  protected GuiceScoped guiceScoped;

  @Inject
  public AbsCommon(GuiceScoped guiceScoped) {
    this.guiceScoped = new GuiceScoped();
    this.driver = guiceScoped.driver;
    this.annotationUtils = new AnnotationUtils();
    this.actionUtils = new ActionUtils(driver);
    this.waiters = new Waiters(driver);
    PageFactory.initElements(driver, this);
  }

  protected void initPage(){
    PageFactory.initElements(driver, this);
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

}
