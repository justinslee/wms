package controllers;

import java.util.List;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
      List<models.Warehouse> warehouses = models.Warehouse.find().all();
      return ok(index.render(warehouses));
    }
}
