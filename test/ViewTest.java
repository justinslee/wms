import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;
import org.junit.Test;
import pages.EditWarehousePage;
import pages.HomePage;
import pages.NewWarehousePage;
import play.libs.F.Callback;
import play.test.TestBrowser;
import static org.fest.assertions.Assertions.assertThat;


public class ViewTest {
  
  @Test
  public void testHomePage () {
    running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        HomePage homePage = new HomePage(browser.getDriver(), 3333);
        browser.goTo(homePage);
        homePage.isAt();
        homePage.gotoNewWarehouse();
      }
    });
  }
  
  @Test
  public void testNewWarehousePage () {
    running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        // Create the pages. 
        NewWarehousePage warehousePage = new NewWarehousePage(browser.getDriver(), 3333); 
        HomePage homePage = new HomePage(browser.getDriver(), 3333);
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
  public void testEditWarehousePage () {
    running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        // Define the pages. 
        NewWarehousePage warehousePage = new NewWarehousePage(browser.getDriver(), 3333); 
        HomePage homePage = new HomePage(browser.getDriver(), 3333);
        // Test that we can create a page.
        browser.goTo(warehousePage);
        warehousePage.isAt();
        String warehouseId = "NewTestWarehouseId";
        warehousePage.makeNewWarehouse(warehouseId);
        homePage.isAt();
        homePage.pageSource().contains(warehouseId);
        // Test that we can edit a page. 
        //   We should really get the PK from the home page, not just magically know it. 
        EditWarehousePage editPage = new EditWarehousePage(browser.getDriver(), 3333, 1);
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

}
