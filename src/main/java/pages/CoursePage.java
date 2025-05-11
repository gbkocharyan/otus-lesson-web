package pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import support.GuiceScoped;

public class CoursePage extends AbsBasePage {

  @Inject
  public CoursePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  @FindBy(xpath = "//main//h1")
  private WebElement courseName;

  public boolean isCorrectCoursePageOpened(String courseTitle) {
    return getText(courseName).contains(courseTitle);
  }
}
