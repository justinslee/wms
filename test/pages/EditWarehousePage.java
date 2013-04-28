package pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

public class EditWarehousePage extends FluentPage {
  private String url;
  
  public EditWarehousePage (WebDriver webDriver, int port, int primaryKey) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/warehouse/" + primaryKey;
  }
  
  public String getUrl() {
    return this.url;
  }
  
  public void isAt() {
    assert(title().equals("WMS: Update Warehouse"));
  }
  
  public void editWarehouse(String newWarehouseId) {
    fill("#warehouseId").with(newWarehouseId);
    fill("#name").with("MyWarehouse");
    fill("#city").with("Honolulu");
    fill("#state").with("Hawaii");
    fill("#zip").with("96822");
    submit("#submit");
  }
  
  public void deleteWarehouse() {
    click("#delete");
  }
}
