package server.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import medicionemisiones.Consumo;
import medicionemisiones.FactorEmision;
import medicionemisiones.FuenteEnergetica;
import medicionemisiones.TipoConsumo;
import organizacion.Organizacion;
import repositorios.RepositorioOrganizacion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utils.Session;

import spark.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;
import static spark.Spark.*;

public class MedicionesController extends Controller implements WithGlobalEntityManager, TransactionalOps {

	public ModelAndView getFormularioMedicion(Request req, Response res) {
		verificarSesion(req, res);
		Map<String, Object> model = Session.getModelWithSession(req);

		return new ModelAndView(model, "mediciones.hbs");
	}

	public ModelAndView registrarMedicion(Request req, Response res) throws FileNotFoundException {
		verificarSesion(req, res);
		Map<String, Object> model = Session.getModelWithSession(req);

		RepositorioOrganizacion.getInstance().persistirConsumoEnOrg(req.session().attribute("idorg"),
				req.queryParams("FuenteEnergetica"), req.queryParams("unidadMedida"), req.queryParams("equivalencia"),
				req.queryParams("Perioricidad"), req.queryParams("valor"), req.queryParams("mes"),
				req.queryParams("año"));

		res.redirect("/");

		return null;
	}

	public ModelAndView registrarMedicionXarchivo(Request req, Response res) throws IOException, ServletException {
		verificarSesion(req, res);
		Map<String, Object> model = Session.getModelWithSession(req);

		File uploadDir = new File("/upload.txt");
		uploadDir.mkdir(); // create the upload directory if it doesn't exist

		Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");

		req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

		try (InputStream input = req.raw().getPart("archivoCsv").getInputStream()) { // getPart needs to use same "name"
																						// as input field in form
			Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);

			RepositorioOrganizacion.getInstance().persistirConsumoEnOrgPorArchivo(req.session().attribute("idorg"),
					tempFile.toString());

		}

		res.redirect("/");

		return null;
	}

	public ModelAndView mostrarMediciones(Request req, Response res) {
		verificarSesion(req, res);
		Map<String, Object> model = Session.getModelWithSession(req);
		entityManager().clear();
		List<Consumo> consumos = RepositorioOrganizacion.getInstance()
				.ObtenerConsumosDeOrg(req.session().attribute("idorg"));
		model.put("consumos", consumos);

		return new ModelAndView(model, "misMediciones.hbs");
	}

}
