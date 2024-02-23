package repositorios;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import organizacion.Organizacion;
import organizacion.Recomendacion;

public class GuiaRecomendaciones implements WithGlobalEntityManager{
	
	 private static GuiaRecomendaciones instance;

	  public static GuiaRecomendaciones getInstance() {
	    if (instance == null)
	      instance = new GuiaRecomendaciones();

	    return instance;
	  }

	  public List<Recomendacion> listar() {
	    return entityManager()
	        .createQuery("from Recomendacion", Recomendacion.class)
	        .getResultList();
	  }

}
