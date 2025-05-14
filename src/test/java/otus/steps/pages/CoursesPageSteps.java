package otus.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import pages.CoursesPage;
import support.GuiceScoped;
import utils.CoursesHelper;
import java.util.Map;

public class CoursesPageSteps {

  @Inject
  CoursesPage coursesPage;

  @Inject
  GuiceScoped guiceScoped;

  CoursesHelper coursesHelper = new CoursesHelper();

  @Пусть("Открыта страница каталога курсов")
  public void openCoursesPage() {
    coursesPage.open();
  }

  @Если("Кликнуть по случайному курсу")
  public void clickOnRandomCourse() {
    String courseTitle = coursesPage.selectRandomCourseTitle();
    coursesPage.clickOnCourseByTitle(courseTitle);
    guiceScoped.store(courseTitle, "courseTitle");
  }

  @Если("Кликнуть по Подготовительные курсы")
  public void selectOnboardingCourses() {
    coursesPage.clickInOnboardingCourses();
  }

  @Тогда("В консоль выводится информация о курсах стартующих в {string} или позже")
  public void findCoursesByGivenDateAndPrintInfo(String date) {
    coursesHelper.validateAndPrintCourseData(coursesPage, coursesPage.getAllCoursesFromGivenDate(date));
  }

  @Тогда("Найти самый дорогой и самый дешевый курс")
  public void getCheapestAndMostExpensiveCourse() {
    Map<String, String> coursesNamesAndPrices = coursesPage.getAllCoursesNamesAndPrices();
    Map.Entry<String, String> cheapestCourse = coursesHelper.getCheapestCourse(coursesNamesAndPrices);
    Map.Entry<String, String> mostExpensiveCourse = coursesHelper.getMostExpensiveCourse(coursesNamesAndPrices);

    System.out.println("Cheapest course: " + cheapestCourse.getKey() + " - " + cheapestCourse.getValue());
    System.out.println("Most expensive course: " + mostExpensiveCourse.getKey() + " - " + mostExpensiveCourse.getValue());
  }

}
