package pages;

import org.fluentlenium.core.FluentPage;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import org.openqa.selenium.WebDriver;


public class StockItemCreatePage extends FluentPage {
  private String url;
  
  public StockItemCreatePage (WebDriver webDriver, int port) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/stockitem/create";
  }
  
  public String getUrl() {
    return this.url;
  }
  
  public void isAt() {
    assert(title().equals("WMS: Create StockItem"));
  }
  
  public void makeNewStockItem(String stockItemId, String productName, String warehouseName) {
    fill("#stockItemId").with(stockItemId);
    find("select", withId("productName")).find("option", withText(productName)).click();
    find("select", withId("warehouseName")).find("option", withText(warehouseName)).click();
    fill("#quantity").with("25");
    submit("#create");
  }
}
