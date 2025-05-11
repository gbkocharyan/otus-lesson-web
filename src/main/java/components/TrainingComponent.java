package components;

import annotations.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import support.GuiceScoped;
import java.util.List;

@Component("xpath://nav/div[3]/div")
public class TrainingComponent extends AbsBaseBlock {

  public TrainingComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  @FindBy(xpath = "//div[3]/div/div/div[1]/div/div/a")
  private List<WebElement> categoryList;

  public String clickOnRandomCategoryAndGetName() {
    waitForComponentVisibility();
    int randomIndex = (int) (Math.random() * categoryList.size());
    String categoryName = getText(categoryList.get(randomIndex)).split(" \\(")[0];
    click(categoryList.get(randomIndex));
    return categoryName;
  }

}
