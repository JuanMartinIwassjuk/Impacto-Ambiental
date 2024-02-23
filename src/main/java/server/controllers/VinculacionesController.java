package server.controllers;

import java.util.Map;
import java.util.NoSuchElementException;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import java.util.List;
import organizacion.Miembro;
import organizacion.Area;
import organizacion.Organizacion;
import organizacion.Vinculacion;
import repositorios.RepositorioOrganizacion;
import repositorios.RepositorioArea;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utils.Session;

public class VinculacionesController extends Controller implements WithGlobalEntityManager, TransactionalOps {

	public ModelAndView mostrarPropuestasDeVinculacion(Request req, Response res) { // la org va a ver las propuestas
																					// que le hicieron
		verificarSesion(req, res);
		Map<String, Object> model = Session.getModelWithSession(req);
		entityManager().clear();
		model.put("vinculaciones", RepositorioOrganizacion.getInstance()
				.getVinculaciones(req.session().attribute("idorg")));

		return new ModelAndView(model, "vinculaciones.html.hbs");
	}

	public ModelAndView mostrarPropuestarParaAceptar(Request req, Response res) {
		verificarSesion(req, res);
		Map<String, Object> model = Session.getModelWithSession(req);
		entityManager().clear();
		model.put("vinculaciones", RepositorioOrganizacion.getInstance()
				.getVinculaciones(req.session().attribute("idorg")));

		return new ModelAndView(model, "vinculacionesAceptar.hbs");
	}

	public ModelAndView aceptarPropuestasOrganizacion(Request req, Response res) {
		try {
			verificarSesion(req, res);
			entityManager().clear();
			
			RepositorioOrganizacion.getInstance().persistirMiembroEnOrg(
					req.session().attribute("idorg"),Integer.parseInt(req.queryParams("docAceptado")));

			res.redirect("/vinculacionesaaceptar");
			                                      

			return null;

		} catch (NoSuchElementException e) {
		
			entityManager().clear();
			RepositorioOrganizacion.getInstance().rechazarVinculacionDeOrg(
					req.session().attribute("idorg"), Integer.parseInt(req.queryParams("docRechazado")));
			res.redirect("/vinculaciones");
			
			

			return null;
		}
	}

	public ModelAndView mostrarPropuestarParaRechazar(Request req, Response res) {
		verificarSesion(req, res);
		Map<String, Object> model = Session.getModelWithSession(req);
		entityManager().clear();
		model.put("vinculaciones", RepositorioOrganizacion.getInstance()
				.getVinculaciones(req.session().attribute("idorg")));

		return new ModelAndView(model, "vinculacionesRechazar.hbs");
	}

	public ModelAndView rechazarPropuestasOrganizacion(Request req, Response res) {
		verificarSesion(req, res);
		entityManager().clear();
		RepositorioOrganizacion.getInstance().rechazarVinculacionDeOrg(req.session().attribute("idorg"),
				Integer.parseInt(req.queryParams("docRechazado")));

		res.redirect("/vinculacionesarechazar");

		return null;
	}

	public ModelAndView mostrarOfertas(Request req, Response res) {
		verificarSesion(req, res);
		List<Organizacion> orgs = RepositorioOrganizacion.getInstance().getOrganizaciones();
		entityManager().clear();
		Map<String, Object> model = Session.getModelWithSession(req);
		model.put("organizaciones", orgs);
		model.put("areas", orgs);

		return new ModelAndView(model, "ofertasVinculacion.html.hbs");
	}

	public ModelAndView oportunidades(Request req, Response res) {
		verificarSesion(req, res);
		entityManager().clear();
		List<Organizacion> orgs = RepositorioOrganizacion
				.getInstance()
				.listarDisponiblesParaUsuario(Session.getAttribute(req, "user_documento"));

		Map<String, Object> model = Session.getModelWithSession(req);
		model.put("organizaciones", orgs);

		return new ModelAndView(model, "oportunidades-vinculacion.html.hbs");
	}
	
	public ModelAndView formulario(Request req, Response res) {
		verificarSesion(req, res);
		String idOrg = req.params(":idOrg");
		String idArea = req.params(":idArea");

		Map<String, Object> model = Session.getModelWithSession(req);
		model.put("idOrg", idOrg);
		model.put("idArea", idArea);

		return new ModelAndView(model, "formulario-vinculacion.html.hbs");
	}

	public ModelAndView crearSolicitudVinculacion(Request req, Response res) {
		verificarSesion(req, res);
        entityManager().clear();
		RepositorioOrganizacion.getInstance().persistirVinculacion(
				Long.parseLong(req.params(":idArea")),
				Long.parseLong(req.params(":idOrg")),
				req.queryParams("nombre"),
				req.queryParams("apellido"),
				Session.getAttribute(req, "user_documento"),
				req.queryParams("motivo"));

		res.redirect("/");
		return null;
	}



public ModelAndView getMiembros(Request req, Response res) {
	verificarSesion(req, res);
	entityManager().clear();
	List<Miembro> miembros = RepositorioOrganizacion
			.getInstance()
			.buscarOrganizacionPorID(req.session().attribute("idorg")).getAreas().get(0).getMiembros();

	Map<String, Object> model = Session.getModelWithSession(req);
	model.put("miembros", miembros);

	return new ModelAndView(model, "miembros.hbs");
}

}
