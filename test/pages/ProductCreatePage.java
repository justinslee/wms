package pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

public class ProductCreatePage extends FluentPage {
  private String url;
  
  public ProductCreatePage (WebDriver webDriver, int port) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/product/create";
  }
  
  public String getUrl() {
    return this.url;
  }
  
  public void isAt() {
    assert(title().equals("WMS: Create Product"));
  }
  
  // For testing purposes, use the same string for both ID and name.
  public void makeNewProduct(String productId) {
    fill("#productId").with(productId);
    fill("#name").with(productId);
    fill("#description").with("A nice product");
    submit("#create");
  }
}
