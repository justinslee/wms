package controllers;

import java.util.List;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import static play.data.Form.form;


public class Tag extends Controller {
  
  public static Result index() {
    List<models.Tag> Tags = models.Tag.find().findList();
    return ok(Tags.isEmpty() ? "No Tags" : Tags.toString());
  } 
  
  public static Result details(String tagId) {
    models.Tag Tag = models.Tag.find().where().eq("tagId", tagId).findUnique();
    return (Tag == null) ? notFound("No Tag found") : ok(Tag.toString());
  }
  
  public static Result newTag(){
    // Create a Tag form and bind the request variables to it.
    Form<models.Tag> TagForm = form(models.Tag.class).bindFromRequest(); 
    // Validate the form values. 
    if (TagForm.hasErrors()) {
      return badRequest("Tag name cannot be 'tag'.");
    }
    // form is OK, so make a Tag and save it.
    models.Tag Tag =  TagForm.get();
    Tag.save();
    return ok(Tag.toString());
  }
  
  public static Result delete(String TagId) {
    models.Tag Tag = models.Tag.find().where().eq("tagId", TagId).findUnique();
    if (Tag != null) {
      Tag.delete();
    }
    return ok();
  }

}
