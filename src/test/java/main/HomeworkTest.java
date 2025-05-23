package main;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.inject.Inject;
import components.HeaderComponent;
import components.TrainingComponent;
import extensions.UIExtension;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CoursePage;
import pages.CoursesPage;
import pages.MainPage;
import utils.CoursesValidationHelper;

@ExtendWith(UIExtension.class)
public class HomeworkTest {

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

  CoursesValidationHelper coursesValidationHelper = new CoursesValidationHelper();

  @Test()
  @DisplayName("Find a course by name in the course catalog page, click on the course tile and check that the correct course page is open")
  public void shouldOpenCorrectCoursePage() {
    coursesPage.open();
    String courseTitle = coursesPage.selectRandomCourseTitle();
    coursesPage.clickOnCourseByTitle(courseTitle);
    assertTrue(coursePage.isCorrectCoursePageOpened(courseTitle),
        courseTitle + " course page will be opened");
  }

  @Test
  @DisplayName("Find courses with earliest and latest start dates and check that the card of the earliest/latest course"
      + " displays the correct course start date and name")
  public void findCoursesWithEarliestAndLatestStartDates() {
    coursesPage.open();

    coursesValidationHelper.validateCourseData(coursesPage, coursesPage.getEarliestCoursesDates(), "Earliest courses dates", softAssert);
    coursesValidationHelper.validateCourseData(coursesPage, coursesPage.getLatestCoursesDates(), "Latest courses dates", softAssert);

    softAssert.assertAll();
  }

  @Test
  @DisplayName("Select a random category in Training menu and verify that the correct category is opened")
  public void selectRandomCategoryAndVerify() {
    mainPage.open();
    headerComponent.moveToTrainingField();
    String categoryName = trainingComponent.clickOnRandomCategoryAndGetName();
    assertTrue(coursesPage.isCategorySelected(coursesPage.getSelectedCategoryByName(categoryName)),
        categoryName + " category is selected");
  }

}
