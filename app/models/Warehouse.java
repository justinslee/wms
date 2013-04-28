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
  @Required 
  public String city;
  @Required
  public String state;
  @Required
  public String zip;
  
  @OneToMany(mappedBy="warehouse", cascade=CascadeType.ALL)
  public List<StockItem> stockitems = new ArrayList<>();
  
  public Warehouse(String warehouseId, String name, String city, String state, String zip) {
    this.warehouseId = warehouseId;
    this.name = name;
    this.city = city;
    this.state = state;
    this.zip = zip;
  }
  
  public static Finder<Long, Warehouse> find() {
    return new Finder<Long, Warehouse>(Long.class, Warehouse.class);
  }
  
  public static List<String> getNames() {
    List<String> warehouseNames = new ArrayList<>();
    for (Warehouse warehouse : find().all()) {
      warehouseNames.add(warehouse.name);
    }
    return warehouseNames;
  }
  
}
