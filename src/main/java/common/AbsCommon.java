package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.ActionUtils;
import utils.AnnotationUtils;
import utils.WaitUtils;

public abstract class AbsCommon<T> {

  protected WebDriver driver;
  protected AnnotationUtils annotationUtils;
  protected ActionUtils actionUtils;
  protected WaitUtils waitUtils;

  public AbsCommon(WebDriver driver) {
    this.driver = driver;
    this.annotationUtils = new AnnotationUtils();
    this.actionUtils = new ActionUtils(driver);
    this.waitUtils = new WaitUtils(driver);
    PageFactory.initElements(driver, this);
  }

}
