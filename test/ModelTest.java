import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import models.Product;
import models.StockItem;
import models.Tag;
import models.Warehouse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.test.FakeApplication;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;
import static play.test.Helpers.stop;


public class ModelTest {
  private FakeApplication application; 
  
  @Before 
  public void startApp() { 
    application = fakeApplication(inMemoryDatabase()); 
    start(application); 
  } 

  @After 
  public void stopApp() { 
    stop(application); 
  } 
  
  @Test
  public void testModel() {
    // Create 1 tag that's associated with 1 product.
    Tag tag = new Tag("Tag");
    Product product = new Product("P-01", "French Press", "Coffee Maker");
    product.tags.add(tag);
    tag.products.add(product);
    
    // Create 1 warehouse that's associated with 1 StockItem for 1 Product
    Warehouse warehouse = new Warehouse("W-01", "Honolulu Warehouse");
    StockItem stockitem = new StockItem(warehouse, product, 100);
    warehouse.stockitems.add(stockitem);
    stockitem.warehouse = warehouse;

    // Persist the sample model by saving all entities and relationships.
    warehouse.save();
    tag.save();
    product.save();
    stockitem.save();
    
    // Retrieve the entire model from the database.
    List<Warehouse> warehouses = Warehouse.find().findList();
    List<Tag> tags = Tag.find().findList();
    List<Product> products = Product.find().findList();
    List<StockItem> stockitems = StockItem.find().findList();
    
    // Check that we've recovered all our entities.
    assertEquals("Checking warehouse", warehouses.size(), 1);
    assertEquals("Checking tags", tags.size(), 1);
    assertEquals("Checking products", products.size(), 1);
    assertEquals("Checking stockitems", stockitems.size(), 1);  
    
    // Check that we've recovered all relationships
    assertEquals("Warehouse-StockItem", warehouses.get(0).stockitems.get(0), stockitems.get(0));
    assertEquals("StockItem-Warehouse", stockitems.get(0).warehouse, warehouses.get(0));
    assertEquals("Product-StockItem", products.get(0).stockitems.get(0), stockitems.get(0));
    assertEquals("StockItem-Product", stockitems.get(0).product, products.get(0));
    assertEquals("Product-Tag", products.get(0).tags.get(0), tags.get(0));
    assertEquals("Tag-Product", tags.get(0).products.get(0), products.get(0));

    
    // Some code to illustrate model manipulation with ORM.
    // Start in Java. Delete the tag from the (original) product instance.
    product.tags.clear();
    // Persist the revised product instance.
    product.save();
    // FYI: this does not change our previously retrieved instance from the database.
    assertTrue("Previously retrieved product still has tag", !products.get(0).tags.isEmpty());
    // But of course it does change a freshly retrieved product instance.
    assertTrue("Fresh Product has no tag", Product.find().findList().get(0).tags.isEmpty());
    // Note: Freshly retrieved Tag does not point to any Product.
    assertTrue("Fresh Tag has no Products", Tag.find().findList().get(0).products.isEmpty());
    // We can now delete this Tag from the database if we want.
    tag.delete();
    assertTrue("No more tags in database", Tag.find().findList().isEmpty());
  }
}
