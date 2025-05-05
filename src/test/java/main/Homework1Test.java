package main;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.inject.Inject;
import components.HeaderComponent;
import components.TrainingComponent;
import extensions.UIExtension;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CoursePage;
import pages.CoursesPage;
import pages.MainPage;

@ExtendWith(UIExtension.class)
public class Homework1Test {

  SoftAssertions softAssert = new SoftAssertions();

  @Inject
  MainPage mainPage;

  @Inject
  CoursesPage coursesPage;

  @Inject
  CoursePage coursePage;

  @Inject
  HeaderComponent headerComponent;

  @Inject
  TrainingComponent trainingComponent;

  @Test
  public void shouldOpenCorrectCoursePage() {
    coursesPage.open();
    String courseTitle = coursesPage.selectRandomCourseTitle();
    coursesPage.clickOnCourseByTitle(courseTitle);
    assertTrue(coursePage.isCorrectCoursePageOpened(courseTitle),
        courseTitle + " course page will be opened");
  }

  @Test
  public void findCoursesWithEarliestAndLatestStartDates() {
    coursesPage.open();
    coursesPage.getEarliestCoursesDates().forEach(courseDate ->
        softAssert.assertThat(coursesPage.isCourseDateInPage(courseDate))
            .as("Earliest courses dates are present in the page")
            .isTrue());
    coursesPage.getLatestCoursesDates().forEach(courseDate ->
        softAssert.assertThat(coursesPage.isCourseDateInPage(courseDate))
            .as("Latest courses dates are present in the page")
            .isTrue());
    softAssert.assertAll();
  }

  @Test
  public void selectRandomCategoryAndVerify() {
    mainPage.open();
    headerComponent.moveToTrainingField();
    String categoryName = trainingComponent.clickOnRandomCategoryAndGetName();
    assertTrue(coursesPage.isCategorySelected(coursesPage.getSelectedCategoryByName(categoryName)),
        categoryName + " category is selected");
  }

}
