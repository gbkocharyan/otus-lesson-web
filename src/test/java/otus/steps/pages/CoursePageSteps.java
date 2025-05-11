package otus.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Тогда;
import pages.CoursePage;
import support.GuiceScoped;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoursePageSteps {

  @Inject
  CoursePage coursePage;

  @Inject
  GuiceScoped guiceScoped;

  @Тогда("Страница курса успешно открыта")
  public void shouldOpenCorrectCoursePage() {
    String courseTitle = guiceScoped.retrieve("courseTitle");
    assertTrue(coursePage.isCorrectCoursePageOpened(courseTitle),
        courseTitle + " course page will be opened");
  }
}
