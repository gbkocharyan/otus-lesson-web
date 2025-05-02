package pages;

import annotations.Path;
import common.AbsCommon;
import org.openqa.selenium.WebDriver;

public abstract class AbsBasePage extends AbsCommon {

  public AbsBasePage(WebDriver driver) {
    super(driver);
  }

  private String baseUrl = System.getProperty("baseUrl");

  private String getPath() {
    return annotationUtils.getAnnotationInstance(this.getClass(), Path.class).value();
  }

  public String getUrl() {
    baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.lastIndexOf("/")) : baseUrl;
    return baseUrl + getPath();
  }

  public void open() {
    driver.get(getUrl());
  }

}
