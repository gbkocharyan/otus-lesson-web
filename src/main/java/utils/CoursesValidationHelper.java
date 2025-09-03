package utils;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import pages.CoursesPage;
import java.util.List;

@Component
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
