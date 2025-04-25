package pages;

import annotations.Path;
import common.AbsCommon;
import org.openqa.selenium.WebDriver;

import java.lang.annotation.Annotation;

public abstract class AbsBasePage extends AbsCommon {

  public AbsBasePage(WebDriver driver) {
    super(driver);
  }

  private String baseUrl = System.getProperty("baseUrl");

  private <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
    if (getClass().isAnnotationPresent(annotationClass)) {
      return getClass().getDeclaredAnnotation(annotationClass);
    }
    throw new RuntimeException(String.format("Class %s has no annotation @Path", getClass().getCanonicalName()));
  }

  private String getPath() {
    return getAnnotation(Path.class).value();
  }

  public String getUrl() {
    baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.lastIndexOf("/")) : baseUrl;
    return baseUrl + getPath();
  }

  public void open() {
    driver.get(getUrl());
  }

}
