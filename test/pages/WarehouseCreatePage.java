package pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

public class WarehouseCreatePage extends FluentPage {
  private String url;
  
  public WarehouseCreatePage (WebDriver webDriver, int port) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/warehouse/create";
  }
  
  public String getUrl() {
    return this.url;
  }
  
  public void isAt() {
    assert(title().equals("WMS: Create Warehouse"));
  }
  
  public void makeNewWarehouse(String warehouseId) {
    fill("#warehouseId").with(warehouseId);
    fill("#name").with("MyWarehouse");
    fill("#city").with("Honolulu");
    fill("#state").with("Hawaii");
    fill("#zip").with("96822");
    submit("#create");
  }
}
