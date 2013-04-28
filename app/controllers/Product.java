package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.productCreate;
import views.html.productEdit;
import static play.data.Form.form;

public class Product extends Controller {
  
  /**
   * Displays a form with default values for creating a new product.
   * @return A product form with default values. 
   */
  public static Result create() {
    models.Product defaults = new models.Product("P01", "MyProduct", "Description");
    Form<models.Product> productForm = form(models.Product.class).fill(defaults);
    return ok(productCreate.render(productForm));
  }
  
  /**
   * Stores a newly created product defined by user.
   * @return The home page. 
   */
  public static Result save() {
    Form<models.Product> productForm = form(models.Product.class).bindFromRequest();
    if (productForm.hasErrors()) {
      return badRequest(productCreate.render(productForm));
    }
    models.Product product = productForm.get();
    product.save();
    return redirect(routes.Application.index());
  }
  
  /**
   * Displays a product's data for updating.
   * @param primaryKey The PK used to retrieve the product. 
   * @return An filled product form.
   */
  public static Result edit(Long primaryKey) {
    models.Product product = models.Product.find().byId(primaryKey);
    product.setStockItemList();
    Form<models.Product> productForm = form(models.Product.class).fill(product);
    return ok(productEdit.render(primaryKey, productForm));
  }
  
 
  /**
   * Saves an updated version of the product data provided by user. 
   * @param primaryKey The PK to the product.
   * @return The home page. 
   */
  public static Result update(Long primaryKey) {
    Form<models.Product> productForm = form(models.Product.class).bindFromRequest();
    if (productForm.hasErrors()) {
      return badRequest(productEdit.render(primaryKey, productForm));
    }
    productForm.get().update(primaryKey);
    return redirect(routes.Application.index());
  }
  
  /**
   * Deletes the product. 
   * @param primaryKey The PK to the product to be deleted.
   * @return The home page. 
   */
  public static Result delete(Long primaryKey) {
    models.Product.find().byId(primaryKey).delete();
    return redirect(routes.Application.index());
  }
}
