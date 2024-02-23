package server.controllers;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class RegisterController {
  public ModelAndView mostrarMenuRegistro(Request req, Response res) {
    Map<String, Object> modelo = new HashMap<>();
    modelo.put("SesionIniciada", req.session().attribute("user_id") != null);
    modelo.put("RolOrganizacion", req.session().attribute("user_rol") == "organizacion");
    modelo.put("RolMiembro", req.session().attribute("user_rol") == "miembro");
    modelo.put("IDuser", req.session().attribute("user_id"));
    return new ModelAndView(modelo, "registro.html.hbs");
  }

}
