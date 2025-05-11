package otus.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Пусть;
import pages.MainPage;

public class MainPageSteps {

  @Inject
  MainPage mainPage;

  @Пусть("Открыта главная страница в браузере")
  public void openPage() {
    mainPage.open();
  }
}
