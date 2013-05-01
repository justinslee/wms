package pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

public class WarehouseEditPage extends FluentPage {
  private String url;
  
  public WarehouseEditPage (WebDriver webDriver, int port, int primaryKey) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/warehouse/" + primaryKey;
  }
  
  public String getUrl() {
    return this.url;
  }
  
  public void isAt() {
    assert(title().equals("WMS: Update Warehouse"));
  }
  
  // For testing purposes, use the same string for both ID and name.
  public void editWarehouse(String newWarehouseId) {
    fill("#warehouseId").with(newWarehouseId);
    fill("#name").with(newWarehouseId);
    fill("#city").with("Honolulu");
    fill("#state").with("Hawaii");
    fill("#zip").with("96822");
    submit("#update");
  }
  
  public void deleteWarehouse() {
    click("#delete");
  }
}
