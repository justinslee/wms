package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Product extends Model {
  private static final long serialVersionUID = -463965431223831634L;
  @Id
  public Long primaryKey;
  @Required
  public String productId;
  @Required
  public String name;
  public String description;
  @ManyToMany(cascade=CascadeType.ALL)
  public List<Tag> tags = new ArrayList<>();
  @OneToMany(mappedBy="product", cascade=CascadeType.ALL)
  public List<StockItem> stockitems = new ArrayList<>();
  
  public Product(String productId, String name, String description) {
    this.productId = productId;
    this.name = name;
    this.description = description;
  }
  
  public static Finder<Long, Product> find() {
    return new Finder<Long, Product>(Long.class, Product.class);
  }
  
  public String toString() {
    return String.format("[Product %s %s %s]", productId, name, description);
  }
  
}
