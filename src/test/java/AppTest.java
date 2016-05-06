
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import org.sql2o.*; // for DB support
import org.junit.*; // for @Before and @After

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
   public void rootTest() {
     goTo("http://localhost:4567/");
     assertThat(pageSource()).contains("Lowe's Hair Salon");
     assertThat(pageSource()).contains("hairstylist");
     assertThat(pageSource()).contains("client");
   }
    //
    //  @Test
    //    public void StylistIsDisplayedTest() {
    //   Stylist newStylist = new Stylist ("Sabrina");
    //   newStylist.save();
    //   String stylistPath = String.format("http://localhost:4567/stylists/%d", newStylist.getId());
    //   goTo(stylistPath);
    //   assertThat(pageSource()).contains("Sabrina");
    // }
  @Test
  public void StylistShowPage() {
    Stylist newStylist = new Stylist("Sabrina");
    newStylist.save();
    Client newClient = new Client("Lataevia", newClient.getId());
    newClient.save();
    String stylistPath = String.format("http://localhost:4567/stylists/%d", newStylist.getId());
    goTo(stylistPath);
    click("a", withText("Lataevia"));
    assertThat(pageSource()).contains("Lataevia");
    assertThat(pageSource()).contains("Go back");
  }

}
