package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;
import static play.data.Form.form;
import views.html.warehouseEdit;
import views.html.warehouseCreate;

public class Warehouse extends Controller {

  /**
   * Displays a form with default values for creating a new warehouse.
   * @return A warehouse form with default values. 
   */
  public static Result create() {
    models.Warehouse defaults = new models.Warehouse("W01", "MyWarehouse", "Kailua", "HI", "96734");
    Form<models.Warehouse> warehouseForm = form(models.Warehouse.class).fill(defaults);
    return ok(warehouseCreate.render(warehouseForm));
  }
  
  /**
   * Stores a newly created warehouse defined by user.
   * @return The home page. 
   */
  public static Result save() {
    Form<models.Warehouse> warehouseForm = form(models.Warehouse.class).bindFromRequest();
    if (warehouseForm.hasErrors()) {
      return badRequest(warehouseCreate.render(warehouseForm));
    }
    models.Warehouse warehouse = warehouseForm.get();
    warehouse.save();
    return redirect(routes.Application.index());
  }
  
  /**
   * Displays a warehouse's data for updating.
   * @param primaryKey The PK used to retrieve the warehouse. 
   * @return An filled warehouse form.
   */
  public static Result edit(Long primaryKey) {
    models.Warehouse warehouse = models.Warehouse.find().byId(primaryKey);
    Form<models.Warehouse> warehouseForm = form(models.Warehouse.class).fill(warehouse);
    return ok(warehouseEdit.render(primaryKey, warehouseForm));
  }
  
 
  /**
   * Saves an updated version of the warehouse data provided by user. 
   * @param primaryKey The PK to the warehouse.
   * @return The home page. 
   */
  public static Result update(Long primaryKey) {
    Form<models.Warehouse> warehouseForm = form(models.Warehouse.class).bindFromRequest();
    if (warehouseForm.hasErrors()) {
      return badRequest(warehouseEdit.render(primaryKey, warehouseForm));
    }
    warehouseForm.get().update(primaryKey);
    return redirect(routes.Application.index());
  }
  
  /**
   * Deletes the warehouse. 
   * @param primaryKey The PK to the warehouse to be deleted.
   * @return The home page. 
   */
  public static Result delete(Long primaryKey) {
    models.Warehouse.find().byId(primaryKey).delete();
    return redirect(routes.Application.index());
  }
}
