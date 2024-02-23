package utils;

import spark.Request;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Session {
  public static String getAttribute(Request req, String attribute) {
    return (String) Optional.ofNullable(req.session().attribute(attribute)).orElse("");
  }

  public static Map<String, Object> getModelWithSession(Request req){
    Map<String, Object> model = new HashMap<>();
    model.put("SesionIniciada", req.session().attribute("user_id") != null);
    model.put("RolOrganizacion", Session.getAttribute(req,"user_rol").equals("organizacion"));
    model.put("RolMiembro", Session.getAttribute(req,"user_rol").equals("miembro"));
    model.put("IDuser", Session.getAttribute(req,"user_id"));

    return model;
  }
}
