package server.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import admin.Usuario;
import repositorios.RepositorioOrganizacion;
import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utils.Session;

public class IniciarSesionController extends Controller{

  public ModelAndView mostrarLogin(Request req, Response res) {
    
	  if (!Session.getAttribute(req, "user_id").isEmpty()) {
	      res.redirect("/");
	    }

    Map<String, Object> modelo = new HashMap<>();
    modelo.put("SesionIniciada", false);
    modelo.put("RolOrganizacion", false);
    modelo.put("RolMiembro", false);

    return new ModelAndView(modelo, "IniciarSesion.html.hbs");
  }


  public ModelAndView crearSesion(Request req, Response res) {
    try {
      Usuario usuario = RepositorioUsuarios.getInstance().buscarPorUsuarioYcontrasenia(
        req.queryParams("username"),
        req.queryParams("password")
      );

      req.session().attribute("user_rol", usuario.getRol());
      req.session().attribute("user_id", usuario.getId().toString());
      req.session().attribute("user_documento", usuario.getDocumento());

      if (usuario.getRol().equals("organizacion")) {
        req.session().attribute("idorg", RepositorioOrganizacion.getInstance().ObtenerIdPorNombre(usuario.getUserName()));
      }

      res.redirect("/");
      
      return null;
    } catch (NoSuchElementException e) {
      res.redirect("/iniciarsesion");
      return null;
    }
  }

}


