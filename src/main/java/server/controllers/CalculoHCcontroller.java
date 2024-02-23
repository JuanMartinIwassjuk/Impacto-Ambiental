package server.controllers;

import java.util.Map;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import medicionemisiones.Periodicidad;
import repositorios.RepositorioMiembro;
import repositorios.RepositorioOrganizacion;
import repositorios.RepositorioTipoConsumo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utils.Session;

public class CalculoHCcontroller extends Controller  implements WithGlobalEntityManager{
	
	
  public ModelAndView getPantallaCalculoHC(Request req, Response res) {
	verificarSesion(req,res);
    Map<String, Object> model = Session.getModelWithSession(req);
		model.put("HuellaCarbono", 0);
		model.put("SeccionCalculo", false);

    return new ModelAndView(model, "calculoHC.hbs");
  }

  public ModelAndView calcularHC(Request req, Response res) {
	  
	    verificarSesion(req,res);
 		Map<String, Object> model = Session.getModelWithSession(req);
 		entityManager().clear();
		model.put("HuellaCarbonoAnual",  RepositorioTipoConsumo.getRepositorioTipoConsumo().calcularHCsegunUser(req,Periodicidad.ANUAL));
		model.put("HuellaCarbonoMensual",  RepositorioTipoConsumo.getRepositorioTipoConsumo().calcularHCsegunUser(req,Periodicidad.MENSUAL));
		model.put("SeccionCalculo", true);

    return new ModelAndView(model, "calculoHC.hbs");
  }

 

}
