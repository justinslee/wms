package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class StockItem extends Model {
  private static final long serialVersionUID = -8143420434783395242L;
  @Id
  public Long primaryKey;
  @Required
  public String stockItemId;
  @Transient
  public String productName;
  @Transient
  public String warehouseName;
  //@Required
  @ManyToOne(cascade=CascadeType.PERSIST)
  public Warehouse warehouse;
  //@Required
  @ManyToOne(cascade=CascadeType.PERSIST)
  public Product product;
  @Required
  public long quantity;
  
  public StockItem(Warehouse warehouse, Product product, long quantity) {
    this.warehouse = warehouse;
    this.product = product;
    this.quantity = quantity;
  }
  
  /**
   * For creating default values of stockitems
   * @param stockItemId The default name.
   * @param quantity The default quantity
   */
  public StockItem(String stockItemId, int quantity) {
    this.stockItemId = stockItemId;
    this.quantity = quantity;
  }

  public static Finder<Long, StockItem> find() {
    return new Finder<Long, StockItem>(Long.class, StockItem.class);
  }
  
  /**
   * Set the product and warehouse instance from the strings. 
   * @return Null if OK, a string error message otherwise. 
   */
  public String validate() {
    if (productName != null) {
      product = Product.find().where().eq("name", productName).findUnique();
      if (product == null) {
        return "Could not find product named: " + productName;
      }
    }
    if (warehouseName != null) {
      warehouse = Warehouse.find().where().eq("name", warehouseName).findUnique();
      if (warehouse == null) {
        return "Could not find warehouse named: " + warehouseName;
      }
    }
    // otherwise all good.
    return null;
  }
}
