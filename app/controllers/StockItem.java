package controllers;

import static play.data.Form.form;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.stockitemCreate;
import views.html.stockitemEdit;

public class StockItem extends Controller {

  
  /**
   * Displays a form with default values for creating a new stockItem.
   * @return A stockItem form with default values. 
   */
  public static Result create() {
    System.out.println("Entering SI create");
    models.StockItem defaults = new models.StockItem("ST-01", 25);
    Form<models.StockItem> stockItemForm = form(models.StockItem.class).fill(defaults);
    return ok(stockitemCreate.render(stockItemForm));
  }
  
  /**
   * Stores a newly created stockItem defined by user.
   * @return The home page. 
   */
  public static Result save() {
    System.out.println("Entering SI save");    
    Form<models.StockItem> stockItemForm = form(models.StockItem.class).bindFromRequest();
    if (stockItemForm.hasErrors()) {
      System.out.println("In SI save, got errors" + stockItemForm.errors());
      return badRequest(stockitemCreate.render(stockItemForm));
    }
    models.StockItem stockItem = stockItemForm.get();
    stockItem.save();
    System.out.println("In SI Save, num stockitems is: " + models.StockItem.find().all().size());
    return redirect(routes.Application.index());
  }
  
  /**
   * Displays a stockItem's data for updating.
   * @param primaryKey The PK used to retrieve the stockItem. 
   * @return An filled stockItem form.
   */
  public static Result edit(Long primaryKey) {
    System.out.println("Entering SI edit");    
    models.StockItem stockItem = models.StockItem.find().byId(primaryKey);
    System.out.println("In SI Edit, stockitem is: " + stockItem + " " + 
    models.StockItem.find().all().size());
    Form<models.StockItem> stockItemForm = form(models.StockItem.class).fill(stockItem);
    return ok(stockitemEdit.render(primaryKey, stockItemForm));
  }
  
 
  /**
   * Saves an updated version of the stockItem data provided by user. 
   * @param primaryKey The PK to the stockItem.
   * @return The home page. 
   */
  public static Result update(Long primaryKey) {
    System.out.println("Entering SI update");    
    Form<models.StockItem> stockItemForm = form(models.StockItem.class).bindFromRequest();
    if (stockItemForm.hasErrors()) {
      return badRequest(stockitemEdit.render(primaryKey, stockItemForm));
    }
    stockItemForm.get().update(primaryKey);
    return redirect(routes.Application.index());
  }
  
  /**
   * Deletes the stockItem. 
   * @param primaryKey The PK to the stockItem to be deleted.
   * @return The home page. 
   */
  public static Result delete(Long primaryKey) {
    models.StockItem.find().byId(primaryKey).delete();
    return redirect(routes.Application.index());
  }
  

}
