package server.controllers;

import spark.Request;
import spark.Response;
import utils.Session;

public abstract class Controller {
	
	public static void verificarSesion(Request req, Response res) {
	 if (Session.getAttribute(req, "user_id").isEmpty()) {
	      res.redirect("/");
	    }
	 
	}
	

}
