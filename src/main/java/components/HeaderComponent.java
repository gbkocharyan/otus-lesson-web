package components;

import annotations.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import support.GuiceScoped;

@Component("xpath://*[@id=\"__next\"]/div[2]/div[3]")
public class HeaderComponent extends AbsBaseBlock {

  public HeaderComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  @FindBy(xpath = "//nav/div[2]/span")
  private WebElement trainingField;

  public void moveToTrainingField() {
    waitForComponentVisibility();
    actionUtils.moveToElement(trainingField);
  }
}
