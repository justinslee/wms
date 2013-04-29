package pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

public class StockItemEditPage extends FluentPage {
  private String url;
  
  public StockItemEditPage (WebDriver webDriver, int port, int primaryKey) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/stockitem/" + primaryKey;
  }
  
  public String getUrl() {
    return this.url;
  }
  
  public void isAt() {
    assert(title().equals("WMS: Update StockItem"));
  }
  
  public void editStockItem(String newStockItemId, String productName, String warehouseName) {
    fill("#stockItemId").with(newStockItemId);
    fill("#productName").with(productName);
    fill("#warehouseName").with(warehouseName);
    submit("#update");
  }
  
  public void deleteStockItem() {
    click("#delete");
  }
}
