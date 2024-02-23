package server.controllers;

import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utils.Session;

public class HomeController extends Controller{

  public ModelAndView getHome(Request req, Response res) {
    Map<String, Object> model = Session.getModelWithSession(req);

    return new ModelAndView(model, "Menu_Inicio.html.hbs");
  }
  
  public ModelAndView mostrarRecomendaciones(Request req, Response res) {
	  verificarSesion(req, res);
	  Map<String, Object> model = Session.getModelWithSession(req);
	  return new ModelAndView(model, "recomendaciones.html.hbs");
  }

}
