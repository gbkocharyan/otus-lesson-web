package main;

import com.google.inject.Inject;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.MainPage;

@ExtendWith(UIExtension.class)
public class Homework1Test {

  @Inject
  MainPage mainPage;

  @Test
  public void test() {
    mainPage.open();

  }

}
