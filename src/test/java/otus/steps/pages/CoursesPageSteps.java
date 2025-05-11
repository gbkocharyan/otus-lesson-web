package otus.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Пусть;
import pages.CoursesPage;
import support.GuiceScoped;

public class CoursesPageSteps {

  @Inject
  CoursesPage coursesPage;

  @Inject
  GuiceScoped guiceScoped;

  @Пусть("Открыта страница курсов")
  public void openCoursesPage() {
    coursesPage.open();
  }

  @Если("Кликнуть по случайному курсу")
  public void clickOnRandomCourse() {
    String courseTitle = coursesPage.selectRandomCourseTitle();
    coursesPage.clickOnCourseByTitle(courseTitle);
    guiceScoped.store(courseTitle, "courseTitle");
  }
}
