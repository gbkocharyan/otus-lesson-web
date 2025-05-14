package pages;

import annotations.Path;
import com.google.inject.Inject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import support.GuiceScoped;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Path("/catalog/courses")
public class CoursesPage extends AbsBasePage {

  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy", new Locale("ru"));
  Random random = new Random();

  @Inject
  public CoursesPage(GuiceScoped guiceScoped) {
    super(guiceScoped.getDriver());
  }

  @FindBy(xpath = "//main//section[2]//div[2]//a//h6/div")
  private List<WebElement> coursesTitles;

  @FindBy(xpath = "//main//section[2]//a")
  private List<WebElement> coursePanels;

  @FindBy(xpath = "//main//section[2]//a/div[2]/div/div")
  private List<WebElement> coursesDates;

  @FindBy(xpath = "//section[1]/div[1]/div[2]/div/div/div")
  private List<WebElement> categoryList;

  @FindBy(xpath = "//*[text()='Подготовительные курсы']")
  private WebElement onboardingCourses;

  public String selectRandomCourseTitle() {
    int randomCourseIndex = (int) (Math.random() * coursesTitles.size());
    return getText(coursesTitles.get(randomCourseIndex));
  }

  public void clickOnCourseByTitle(String courseTitle) {
    List<WebElement> matchingCourses = coursePanels.stream()
        .filter(course -> course.getText().trim().contains(courseTitle))
        .toList();

    if (matchingCourses.isEmpty()) {
      throw new RuntimeException(courseTitle + " course not found.");
    }

    WebElement randomCourse = matchingCourses.get(random.nextInt(matchingCourses.size()));
    click(randomCourse);
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

  public List<WebElement> getAllCoursesFromGivenDate(String date) {
    waiters.waitForElementToBeVisible(coursesDates.getFirst());
    LocalDate targetDate = LocalDate.parse(date, formatter);
    return coursesDates.stream()
        .filter(course -> !extractCourseDate(course).isBefore(targetDate))
        .collect(Collectors.toList());
  }

  private LocalDate extractCourseDate(WebElement courseDate) {
    String dateText = courseDate.getText().trim().split(" . ")[0].trim();
    return LocalDate.parse(dateText, this.formatter);
  }

  public WebElement getCourseTitleByDate(WebElement targetCourseDate) {
    WebElement parentPanel = coursePanels.stream()
        .filter(panel -> panel.findElements(By.xpath(".//div[2]/div/div")).stream()
            .anyMatch(courseDate -> courseDate.equals(targetCourseDate)))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("No course panel contains the given date."));

    return parentPanel.findElement(By.xpath(".//h6/div"));
  }


  public boolean isCourseDataInPage(WebElement courseDate, WebElement courseTitle) {
    Document doc = Jsoup.parse(Objects.requireNonNull(driver.getPageSource()));
    String courseDateText = courseDate.getText().trim();
    String courseTitleText = courseTitle.getText().trim();
    String query = String.format("div:contains(%s):has(div:contains(%s))", courseDateText, courseTitleText);
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

  public void clickInOnboardingCourses() {
    click(onboardingCourses);
  }

  public Map<String, String> getAllCoursesNamesAndPrices() {
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    this.initPage();
    Map<String, String> courses = new HashMap<>();
    for (String href : coursePanels.stream()
        .map(link -> link.getAttribute("href"))
        .toList()) {
      try {
        courses.putAll(getCourseDataFromPage(href));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return courses;
  }

  private Map<String, String> getCourseDataFromPage(String href) throws IOException {
    Map<String, String> courseData = new HashMap<>();
    Document doc = Jsoup.connect(href).get();
    String courseTitle = Objects.requireNonNull(doc.select("main h3").first()).text();
    String coursePrice = Objects.requireNonNull(doc.select("div:matchesOwn(\\d+\\s*₽)").first()).text();
    courseData.put(courseTitle, coursePrice);
    return courseData;
  }

}
