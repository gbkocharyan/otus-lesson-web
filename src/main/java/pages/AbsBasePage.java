package pages;

import annotations.Path;
import com.google.inject.Inject;
import common.AbsCommon;
import support.GuiceScoped;

public abstract class AbsBasePage extends AbsCommon {

  @Inject
  public AbsBasePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
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
