package server.controllers;

import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utils.Session;
import organizacion.Organizacion;
import repositorios.RepositorioOrganizacion;

public class ReporteController extends Controller implements WithGlobalEntityManager, TransactionalOps{
	
	  public ModelAndView getReporte(Request req, Response res) {
		    verificarSesion(req, res);
		    Map<String, Object> model = Session.getModelWithSession(req);

		    List<Organizacion> orgGubernamental= RepositorioOrganizacion.getInstance().obtenerOrganizacionesPorClasificacion("GUBERNAMENTAL");

		    List<Organizacion> orgOng= RepositorioOrganizacion.getInstance().obtenerOrganizacionesPorClasificacion("ONG");

		    List<Organizacion> orgEmpresa= RepositorioOrganizacion.getInstance().obtenerOrganizacionesPorClasificacion("EMPRESA");

		    List<Organizacion> orgInstitucion= RepositorioOrganizacion.getInstance().obtenerOrganizacionesPorClasificacion("INSTITUCION");


		    model.put("orgInstitucion", orgInstitucion);
		    model.put("orgEmpresa", orgEmpresa);
		    model.put("orgOng", orgOng);
		    model.put("orgGubernamental", orgGubernamental);

		    return new ModelAndView(model, "reporte.html.hbs");
		  }
	}

