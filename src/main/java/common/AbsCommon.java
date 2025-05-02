package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.ActionUtils;
import utils.AnnotationUtils;
import utils.Waiters;

public abstract class AbsCommon {

  protected WebDriver driver;
  protected AnnotationUtils annotationUtils;
  protected ActionUtils actionUtils;
  protected Waiters waiters;

  public AbsCommon(WebDriver driver) {
    this.driver = driver;
    this.annotationUtils = new AnnotationUtils();
    this.actionUtils = new ActionUtils(driver);
    this.waiters = new Waiters(driver);
    PageFactory.initElements(driver, this);
  }

}
