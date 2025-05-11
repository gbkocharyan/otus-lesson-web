package pages;

import annotations.Path;
import support.GuiceScoped;

@Path("/")
public class MainPage extends AbsBasePage {

  public MainPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }
}
