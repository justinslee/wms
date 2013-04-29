package pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

public class ProductEditPage extends FluentPage {
  private String url;
  
  public ProductEditPage (WebDriver webDriver, int port, int primaryKey) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/product/" + primaryKey;
  }
  
  public String getUrl() {
    return this.url;
  }
  
  public void isAt() {
    assert(title().equals("WMS: Update Product"));
  }
  
  public void editProduct(String newProductId) {
    fill("#productId").with(newProductId);
    fill("#name").with("MyProduct");
    fill("#description").with("A nice product");
    submit("#update");
  }
  
  public void deleteProduct() {
    click("#delete");
  }
}
