package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class StockItem extends Model {
  private static final long serialVersionUID = -8143420434783395242L;
  @Id
  public Long primaryKey;
  @Required
  public String stockItemId;
  @Required
  @ManyToOne(cascade=CascadeType.ALL)
  public Warehouse warehouse;
  @Required
  @ManyToOne(cascade=CascadeType.ALL)
  public Product product;
  @Required
  public long quantity;
  
  public StockItem(Warehouse warehouse, Product product, long quantity) {
    this.warehouse = warehouse;
    this.product = product;
    this.quantity = quantity;
  }
  
  public static Finder<Long, StockItem> find() {
    return new Finder<Long, StockItem>(Long.class, StockItem.class);
  }
}
