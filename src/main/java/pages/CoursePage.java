package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CoursePage extends AbsBasePage {

  public CoursePage(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//main//h1")
  private WebElement courseName;

  public boolean isCorrectCoursePageOpened(String courseTitle) {
    return getText(courseName).contains(courseTitle);
  }
}
