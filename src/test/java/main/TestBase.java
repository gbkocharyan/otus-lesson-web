package main;

import factory.WebDriverFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@SpringBootTest(classes = Application.class)
public class TestBase extends AbstractTestNGSpringContextTests {

  @Autowired
  private WebDriverFactory webDriverFactory;

  @BeforeMethod
  public void setUp(ITestContext context) throws Exception {
    webDriverFactory.create(context);
  }

  @AfterMethod
  public void tearDown() {
    try {
      webDriverFactory.killDriver();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
