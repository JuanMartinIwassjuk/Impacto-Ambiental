package repositorios;

import medicionemisiones.FactorEmision;
import organizacion.Organizacion;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class RepositorioFactorEmision implements WithGlobalEntityManager, TransactionalOps {
  private static RepositorioFactorEmision instance;
  public static RepositorioFactorEmision getInstance() {
    if (instance == null)
      instance = new RepositorioFactorEmision();

    return instance;
  }

  public FactorEmision buscarPorNombre(String nombre) {
    
    return entityManager()
			.createQuery("select t from FactorEmision t where t.unidad like \'" + nombre + " \' ",
					FactorEmision.class)
			.getResultList().get(0);
  }
}
