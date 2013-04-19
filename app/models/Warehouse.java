package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Warehouse extends Model {
  private static final long serialVersionUID = 7268900706085963780L;
  @Id
  public long primaryKey;
  @Required
  public String warehouseId;
  @Required
  public String name;
  @OneToMany(mappedBy="warehouse", cascade=CascadeType.ALL)
  public List<StockItem> stockitems = new ArrayList<>();
  
  public Warehouse(String warehouseId, String name) {
    this.warehouseId = warehouseId;
    this.name = name;
  }
  
  public static Finder<Long, Warehouse> find() {
    return new Finder<Long, Warehouse>(Long.class, Warehouse.class);
  }
}
