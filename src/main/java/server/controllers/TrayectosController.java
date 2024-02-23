package server.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import organizacion.Miembro;
import repositorios.RepositorioMiembro;
import repositorios.RepositorioOrganizacion;
import repositorios.RepositorioTrayecto;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import trayecto.Trayecto;
import utils.Session;

public class TrayectosController extends Controller implements WithGlobalEntityManager, TransactionalOps {

  public ModelAndView listar(Request req, Response res){
    verificarSesion(req, res);

    Map<String, Object> model = Session.getModelWithSession(req);

    if(RepositorioOrganizacion.getInstance().anyMatchUser(Session.getAttribute(req, "user_documento"))){
      List<Trayecto> trayectos = RepositorioTrayecto
        .getInstance()
        .buscarTrayectosDeUsuario(Long.parseLong(Session.getAttribute(req, "user_id")));

      model.put("trayectos", trayectos);
      model.put("perteneceOrganizacion", true);
    }else {
      model.put("perteneceOrganizacion", false);
    }

    return new ModelAndView(model, "trayectos.html.hbs");
  }

  public ModelAndView formularioTrayecto(Request req, Response res) {
    verificarSesion(req, res);

    Map<String, Object> model = Session.getModelWithSession(req);

    return new ModelAndView(model, "formulario-trayecto.html.hbs");
  }

  public ModelAndView crearParcialmenteTrayecto(Request req, Response res){
    verificarSesion(req, res);
    RepositorioTrayecto.getInstance().crearTrayecto(req,res);
    res.redirect("/trayectos");

    return null;
  }

  public ModelAndView agregarTramo(Request req, Response res) {
    verificarSesion(req, res);

    Map<String, Object> model = Session.getModelWithSession(req);
    String idTrayecto = req.params("id");
    req.session().attribute("trayecto_id", idTrayecto);

    return new ModelAndView(model, "formulario-tramo-privado.html.hbs");
  }
  
  public ModelAndView darAltaTramo(Request req, Response res) {
	    verificarSesion(req, res);

	    Map<String, Object> model = Session.getModelWithSession(req);
	    
	    RepositorioTrayecto.getInstance().agregarTramo(req,res);
	    
        res.redirect("/trayectos");
	    return null;
	  }

}
