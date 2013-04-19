package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Tag extends Model {
  private static final long serialVersionUID = -5950706943271500599L;
  @Id
  public Long primaryKey;
  @Required
  public String tagId;
  @ManyToMany(mappedBy="tags", cascade=CascadeType.ALL)
  public List<Product> products = new ArrayList<>();
  
  public Tag (String tagId) {
    this.tagId = tagId;
  }

  /**
   * No tag can be named "Tag". 
   * Note: illustrates use of validate() method.
   * @return null if OK, error string if not OK.
   */
  public String validate() {
    return ("Tag".equals(this.tagId)) ? "Invalid tag name" : null;
  }
  
  public static Finder<Long, Tag> find() {
    return new Finder<Long, Tag>(Long.class, Tag.class);
  }
  
  public String toString() {
    return String.format("[Tag %s ]", tagId);
  }
}
