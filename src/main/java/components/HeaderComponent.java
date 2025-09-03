package components;

import annotations.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Service;

@Service
@Component("xpath://*[@id=\"__next\"]/div[2]/div[3]")
public class HeaderComponent extends AbsBaseBlock {

  @FindBy(xpath = "//nav/div[2]/span")
  private WebElement trainingField;

  public void moveToTrainingField() {
    waiters.waitForElementToBeVisible(trainingField);
    actionUtils.moveToElement(trainingField);
  }
}
