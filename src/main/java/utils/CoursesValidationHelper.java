package utils;

import org.openqa.selenium.WebElement;
import pages.CoursesPage;
import java.util.List;
import org.assertj.core.api.SoftAssertions;

public class CoursesValidationHelper {

  public void validateCourseData(CoursesPage coursesPage, List<WebElement> courseDates, String description, SoftAssertions softAssert) {
    courseDates.forEach(courseDate -> {
      WebElement courseTitle = coursesPage.getCourseTitleByDate(courseDate);
      boolean isCourseDataValid = coursesPage.isCourseDataInPage(courseDate, courseTitle);

      softAssert.assertThat(isCourseDataValid)
          .as(description + " are present in the page for date: " + courseDate)
          .isTrue();
    });
  }

}
