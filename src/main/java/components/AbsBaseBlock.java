package components;

import annotations.Component;
import common.AbsCommon;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import utils.AnnotationUtils;

public abstract class AbsBaseBlock extends AbsCommon {

  @Autowired
  private AnnotationUtils annotationUtils;

  private By componentSelector;

  @PostConstruct
  public void initComponents() {
    initPages();
    initializeSelector();
  }

  public void verifyComponentLoaded() {
    initializeSelector();
    waiters.waitForElementToBeVisible(componentSelector);
  }

  private void initializeSelector() {
    String[] selector = annotationUtils.getAnnotationInstance(this.getClass(), Component.class)
        .value()
        .split(":");

    componentSelector = switch (selector[0].trim()) {
      case "css" -> By.cssSelector(selector[1].trim());
      case "xpath" -> By.xpath(selector[1].trim());
      default -> throw new IllegalArgumentException("Unsupported selector type: " + selector[0]);
    };
  }
}
