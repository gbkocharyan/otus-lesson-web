package pages;

import annotations.Path;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Service;

@Service
@Path("/")
public class CoursePage extends AbsBasePage {

  @FindBy(xpath = "//main//h1")
  private WebElement courseName;

  public boolean isCorrectCoursePageOpened(String courseTitle) {
    initPages();
    waiters.waitForElementToBeVisible(courseName);
    return getText(courseName).contains(courseTitle);
  }
}
