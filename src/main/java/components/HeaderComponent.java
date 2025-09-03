package components;

import annotations.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Service;

@Service
@Component("xpath://*[@id=\"__next\"]/div[1]/div[2]")
public class HeaderComponent extends AbsBaseBlock {

  @FindBy(css = "span[title='Обучение']")
  private WebElement trainingField;

  public void moveToTrainingField() {
    initComponents();
    waiters.waitForElementToBeVisible(trainingField);
    actionUtils.moveToElement(trainingField);
  }
}
