package pages;
import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

public class HomePage extends FluentPage {
  private String url;
  
  public HomePage (WebDriver webDriver, int port) {
    super(webDriver);
    this.url = "http://localhost:" + port;
  }
  
  public String getUrl() {
    return this.url;
  }
  
  public void isAt() {
    assert(title().equals("WMS: Home"));
  }
  
  public void gotoNewWarehouse() {
    click("newWarehouse");
    assert(title().equals("WMS: Create Warehouse"));
  }
}
