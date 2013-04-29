import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;
import org.junit.Test;
import pages.ProductCreatePage;
import pages.ProductEditPage;
import pages.StockItemCreatePage;
import pages.StockItemEditPage;
import pages.WarehouseEditPage;
import pages.IndexPage;
import pages.WarehouseCreatePage;
import play.libs.F.Callback;
import play.test.TestBrowser;
import static org.fest.assertions.Assertions.assertThat;


public class ViewTest {
  
  @Test
  public void testIndexPage () {
    running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        IndexPage homePage = new IndexPage(browser.getDriver(), 3333);
        browser.goTo(homePage);
        homePage.isAt();
        homePage.gotoNewWarehouse();
      }
    });
  }
  
  @Test
  public void testWarehouseCreatePage () {
    running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        // Create the pages. 
        WarehouseCreatePage warehousePage = new WarehouseCreatePage(browser.getDriver(), 3333); 
        IndexPage homePage = new IndexPage(browser.getDriver(), 3333);
        // Now test the page.
        browser.goTo(warehousePage);
        warehousePage.isAt();
        String warehouseId = "NewTestWarehouse";
        warehousePage.makeNewWarehouse(warehouseId);
        homePage.isAt();
        homePage.pageSource().contains(warehouseId);
      }
    });
  }
  
  @Test
  public void testWarehouseEditPage () {
    running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        // Define the pages. 
        WarehouseCreatePage warehousePage = new WarehouseCreatePage(browser.getDriver(), 3333); 
        IndexPage homePage = new IndexPage(browser.getDriver(), 3333);
        // Test that we can create a page.
        browser.goTo(warehousePage);
        warehousePage.isAt();
        String warehouseId = "NewTestWarehouseId";
        warehousePage.makeNewWarehouse(warehouseId);
        homePage.isAt();
        homePage.pageSource().contains(warehouseId);
        // Test that we can edit a page. 
        //   We should really get the PK from the home page, not just magically know it. 
        WarehouseEditPage editPage = new WarehouseEditPage(browser.getDriver(), 3333, 1);
        browser.goTo(editPage);
        String editWarehouseId = "EditedWarehouseId";
        editPage.editWarehouse(editWarehouseId);
        homePage.pageSource().contains(editWarehouseId);
        // Test that we can delete the page and it will no longer be found on the home page. 
        browser.goTo(editPage);
        editPage.deleteWarehouse();
        homePage.isAt();
        assertThat(homePage.pageSource()).doesNotContain(editWarehouseId);
      }
    });
  }
  
  @Test
  public void testProductCreatePage () {
    running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        // Create the pages. 
        ProductCreatePage productPage = new ProductCreatePage(browser.getDriver(), 3333); 
        IndexPage homePage = new IndexPage(browser.getDriver(), 3333);
        // Now test the page.
        browser.goTo(productPage);
        productPage.isAt();
        String id = "NewTestProduct";
        productPage.makeNewProduct(id);
        homePage.isAt();
        homePage.pageSource().contains(id);
      }
    });
  }
  
  @Test
  public void testProductEditPage () {
    running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        // Define the pages. 
        ProductCreatePage productPage = new ProductCreatePage(browser.getDriver(), 3333); 
        IndexPage homePage = new IndexPage(browser.getDriver(), 3333);
        // Test that we can create a page.
        browser.goTo(productPage);
        productPage.isAt();
        String productId = "NewTestProductId";
        productPage.makeNewProduct(productId);
        homePage.isAt();
        homePage.pageSource().contains(productId);
        // Test that we can edit a page. 
        //   We should really get the PK from the home page, not just magically know it. 
        ProductEditPage editPage = new ProductEditPage(browser.getDriver(), 3333, 1);
        browser.goTo(editPage);
        String editProductId = "EditedProductId";
        editPage.editProduct(editProductId);
        homePage.pageSource().contains(editProductId);
        // Test that we can delete the page and it will no longer be found on the home page. 
        browser.goTo(editPage);
        editPage.deleteProduct();
        homePage.isAt();
        assertThat(homePage.pageSource()).doesNotContain(editProductId);
      }
    });
  }
  
  public void testStockItemCreatePage () {
    running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        // create a reference to the home page.
        IndexPage homePage = new IndexPage(browser.getDriver(), 3333);
        // Create a product.
        ProductCreatePage productPage = new ProductCreatePage(browser.getDriver(), 3333); 
        browser.goTo(productPage);
        String productId = "NewTestProduct";
        productPage.makeNewProduct(productId);
        // Create a warehouse.
        WarehouseCreatePage warehousePage = new WarehouseCreatePage(browser.getDriver(), 3333); 
        browser.goTo(warehousePage);
        String warehouseId = "NewTestWarehouse";
        warehousePage.makeNewWarehouse(warehouseId);
        // Now create a StockItem that references the test product and warehouse
        String stockItemId = "SI-01";
        StockItemCreatePage stockItemPage = new StockItemCreatePage(browser.getDriver(), 3333);
        browser.goTo(stockItemPage);
        stockItemPage.makeNewStockItem(stockItemId, productId, warehouseId);
        homePage.isAt();
        homePage.pageSource().contains(stockItemId);
      }
    });
  }
  
  public void testStockItemEditPage () {
    running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        // create a reference to the home page.
        IndexPage homePage = new IndexPage(browser.getDriver(), 3333);
        // Create a product.
        ProductCreatePage productPage = new ProductCreatePage(browser.getDriver(), 3333); 
        browser.goTo(productPage);
        String productId = "NewTestProduct";
        productPage.makeNewProduct(productId);
        // Create a warehouse.
        WarehouseCreatePage warehousePage = new WarehouseCreatePage(browser.getDriver(), 3333); 
        browser.goTo(warehousePage);
        String warehouseId = "NewTestWarehouse";
        warehousePage.makeNewWarehouse(warehouseId);
        // Now create a StockItem that references the test product and warehouse
        String stockItemId = "SI-01";
        StockItemCreatePage stockItemPage = new StockItemCreatePage(browser.getDriver(), 3333);
        browser.goTo(stockItemPage);
        stockItemPage.makeNewStockItem(stockItemId, productId, warehouseId);
        homePage.isAt();
        homePage.pageSource().contains(stockItemId);
        
        // Now we can finally edit it.
        StockItemEditPage editPage = new StockItemEditPage(browser.getDriver(), 3333, 3);
        browser.goTo(editPage);
        String editStockItemId = "EditedStockItemId";
        editPage.editStockItem(editStockItemId, productId, warehouseId);
        homePage.pageSource().contains(editStockItemId);
        // Test that we can delete the page and it will no longer be found on the home page. 
        browser.goTo(editPage);
        editPage.deleteStockItem();
        homePage.isAt();
        assertThat(homePage.pageSource()).doesNotContain(editStockItemId);
      }
    });
  }
  
  

}
