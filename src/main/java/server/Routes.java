package server;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import server.controllers.CalculoHCcontroller;
import server.controllers.HomeController;
import server.controllers.IniciarSesionController;
import server.controllers.MedicionesController;
import server.controllers.RegisterController;
import server.controllers.ReporteController;
import server.controllers.TrayectosController;
import server.controllers.VinculacionesController;
import spark.Spark;
import static spark.Spark.after;

import spark.template.handlebars.HandlebarsTemplateEngine;

public class Routes implements WithGlobalEntityManager, TransactionalOps {

	public static void main(String[] args) {
		System.out.println("Iniciando Server");
		Spark.port(getHerokuAssignedPort());
		System.out.println("Server Iniciado en el puerto " + getHerokuAssignedPort());
		Spark.staticFileLocation("/public");

		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		HomeController homeController = new HomeController();
		RegisterController registerController = new RegisterController();
		IniciarSesionController iniciarSesionController = new IniciarSesionController();
		VinculacionesController vinculacionController = new VinculacionesController();
		TrayectosController trayectosController = new TrayectosController();
		CalculoHCcontroller calculoHc = new CalculoHCcontroller();
		MedicionesController medicionesController = new MedicionesController();
		ReporteController reporteController = new ReporteController();

		//Bootstrap bostrap = new Bootstrap();
		//bostrap.run();

		
		
		Spark.get("/", (req,res)->homeController.getHome(req,res), engine);
		Spark.get("/registro", (req,res)->registerController.mostrarMenuRegistro(req,res), engine);
		Spark.get("/iniciarsesion", (req,res)->iniciarSesionController.mostrarLogin(req,res), engine);
		Spark.post("/iniciarsesion", (req,res)->iniciarSesionController.crearSesion(req,res), engine);
		Spark.get("/vinculaciones", (req,res)->vinculacionController.mostrarPropuestasDeVinculacion(req,res), engine);
		Spark.get("/organizacion/miembros", (req,res) -> vinculacionController.getMiembros(req,res), engine);

		Spark.get("/vinculacionesaaceptar", (req,res)->vinculacionController.mostrarPropuestarParaAceptar(req,res), engine);
		Spark.post("/vinculacionesaaceptar", (req,res)->vinculacionController.aceptarPropuestasOrganizacion(req,res), engine);
		Spark.get("/vinculacionesarechazar", (req,res)->vinculacionController.mostrarPropuestarParaRechazar(req,res), engine);
		Spark.post("/vinculacionesarechazar", (req,res)->vinculacionController.rechazarPropuestasOrganizacion(req,res), engine);
		Spark.get("/ofertasvinculacion", (req,res)->vinculacionController.mostrarOfertas(req,res), engine);

		Spark.get("/oportunidades", (req,res) -> vinculacionController.oportunidades(req,res), engine);
		Spark.get("/organizaciones/:idOrg/areas/:idArea/vinculaciones/solicitud/nueva", (req, res) -> vinculacionController.formulario(req, res), engine);
		Spark.post("/organizaciones/:idOrg/areas/:idArea/vinculaciones/solicitud", (req, res) -> vinculacionController.crearSolicitudVinculacion(req, res), engine);

		Spark.get("/trayectos", (req, res)->trayectosController.listar(req,res), engine);
		Spark.get("/trayectos/nuevo", (req, res)->trayectosController.formularioTrayecto(req,res), engine);
		Spark.get("/trayectos/:id/miembros/nuevo", (req, res)->trayectosController.formularioTrayecto(req,res), engine);
		Spark.get("/trayectos/:id/tramos/nuevo", (req, res)->trayectosController.agregarTramo(req,res), engine);
		Spark.post("/trayectos", (req, res)->trayectosController.crearParcialmenteTrayecto(req,res), engine);
		Spark.post("/tramo", (req, res)->trayectosController.darAltaTramo(req,res), engine);

		Spark.get("/calculohc", (req,res)->calculoHc.getPantallaCalculoHC(req,res), engine);
		Spark.post("/calculohc", (req,res)->calculoHc.calcularHC(req,res), engine);
	
		Spark.get("/medicion", (req,res)->medicionesController.getFormularioMedicion(req,res), engine);
		Spark.post("/medicion", (req,res)->medicionesController.registrarMedicion(req,res), engine);
		Spark.post("/medicion/altaarchivo", (req,res)->medicionesController.registrarMedicionXarchivo(req,res), engine);
		Spark.get("/mediciones", (req,res)->medicionesController.mostrarMediciones(req,res), engine);
		
		Spark.get("/recomendaciones", (req,res)->homeController.mostrarRecomendaciones(req,res), engine);
		
		Spark.get("/reportesconsumo", (req,res)->reporteController.getReporte(req,res), engine);

		after((req, res) -> PerThreadEntityManagers.getEntityManager().clear());
		
	}

	static int getHerokuAssignedPort() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get("PORT") != null) {
			return Integer.parseInt(processBuilder.environment().get("PORT"));
		}

		return 8080;
	}
}
