package pages;

import annotations.Path;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/catalog/courses")
public class CoursesPage extends AbsBasePage {

  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy", new Locale("ru"));

  public CoursesPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//main//section[2]//div[2]//a//h6/div")
  private List<WebElement> coursesTitles;

  @FindBy(xpath = "//main//section[2]//a")
  private List<WebElement> coursePanels;

  @FindBy(xpath = "//main//section[2]//a/div[2]/div/div")
  private List<WebElement> coursesDates;

  @FindBy(xpath = "//section[1]/div[1]/div[2]/div/div/div")
  private List<WebElement> categoryList;

  public String selectRandomCourseTitle() {
    int randomCourseIndex = (int) (Math.random() * coursesTitles.size());
    return getText(coursesTitles.get(randomCourseIndex));
  }

  public void clickOnCourseByTitle(String courseTitle) {
    WebElement webElement = coursePanels.stream()
        .filter(course -> course.getText().trim().contains(courseTitle))
        .findFirst()
        .orElseThrow(() -> new RuntimeException(courseTitle + " course not found."));
    click(webElement);
  }

  public List<WebElement> getEarliestCoursesDates() {
    waiters.waitForElementToBeVisible(coursesDates.getFirst());
    Optional<LocalDate> earliestDateOpt = coursesDates.stream()
        .map(this::extractCourseDate)
        .reduce((date1, date2) -> date1.isBefore(date2) ? date1 : date2);
    if (earliestDateOpt.isEmpty()) {
      return List.of();
    }
    LocalDate earliestDate = earliestDateOpt.get();
    return this.coursesDates.stream()
        .filter(course -> extractCourseDate(course).equals(earliestDate))
        .collect(Collectors.toList());
  }

  public List<WebElement> getLatestCoursesDates() {
    waiters.waitForElementToBeVisible(coursesDates.getFirst());
    Optional<LocalDate> latestDateOpt = coursesDates.stream()
        .map(this::extractCourseDate)
        .reduce(
            (date1, date2) -> date1.isAfter(date2) ? date1 : date2);
    if (latestDateOpt.isEmpty()) {
      return List.of();
    }
    LocalDate latestDate = latestDateOpt.get();
    return coursesDates.stream()
        .filter(course -> extractCourseDate(course).equals(latestDate))
        .collect(Collectors.toList());
  }

  private LocalDate extractCourseDate(WebElement courseDate) {
    String dateText = courseDate.getText().trim().split(" . ")[0].trim();
    return LocalDate.parse(dateText, this.formatter);
  }

  public boolean isCourseDateInPage(WebElement courseDate) {
    Document doc = Jsoup.parse(Objects.requireNonNull(driver.getPageSource()));
    String courseDateText = courseDate.getText().trim();
    String query = String.format("div:contains(%s)", courseDateText);
    Element element = doc.select(query).first();
    return element != null;
  }

  public WebElement getSelectedCategoryByName(String categoryName) {
    return categoryList.stream()
        .filter(category -> category.getText().trim().equalsIgnoreCase(categoryName))
        .findFirst()
        .orElseThrow(() -> new RuntimeException(categoryName + " category not found."));
  }

  public boolean isCategorySelected(WebElement webElement) {
    return getElementAttribute(webElement).equals("true");
  }

}
