package utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.WebElement;
import pages.CoursesPage;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class CoursesHelper {

  public void validateAndPrintCourseData(CoursesPage coursesPage, List<WebElement> courseDates) {
    courseDates.forEach(courseDate -> {
      WebElement courseTitle = coursesPage.getCourseTitleByDate(courseDate);
      boolean isCourseDataValid = coursesPage.isCourseDataInPage(courseDate, courseTitle);

      assertThat(isCourseDataValid)
          .as("Course are present in the page for date: " + courseDate)
          .isTrue();
      System.out.println("Course " + courseTitle.getText() + " starts on " + courseDate.getText().trim().split(" . ")[0].trim());
    });
  }

  public Map.Entry<String, String> getCheapestCourse(Map<String, String> courses) {
    return courses.entrySet().stream()
        .min(Comparator.comparing(entry -> entry.getValue().split(" ")[0]))
        .orElseThrow(() -> new IllegalArgumentException("No courses found"));
  }

  public Map.Entry<String, String> getMostExpensiveCourse(Map<String, String> courses) {
    return courses.entrySet().stream()
        .max(Comparator.comparing(entry -> entry.getValue().split(" ")[0]))
        .orElseThrow(() -> new IllegalArgumentException("No courses found"));
  }

}
