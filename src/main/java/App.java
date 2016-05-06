import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {

    ProcessBuilder process = new ProcessBuilder();
       Integer port;
       if (process.environment().get("PORT") != null) {
           port = Integer.parseInt(process.environment().get("PORT"));
       } else {
           port = 4567;
       }

      setPort(port);


    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("stylits", Stylist.all());
        model.put("template", "templates/index.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      post("/stylists", (request, response) -> {
           HashMap<String, Object> model = new HashMap<String, Object>();
           String stylistName = request.queryParams("stylist-name");
           Stylist newStylist = new Stylist(stylistName);
           newStylist.save();
           response.redirect("/");
           return null;
         });
  }
}
