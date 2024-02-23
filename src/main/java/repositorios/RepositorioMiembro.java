package repositorios;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import organizacion.Miembro;


public class RepositorioMiembro implements WithGlobalEntityManager {
  private static RepositorioMiembro instance;

  public static RepositorioMiembro getInstance() {
    if (instance == null)
      instance = new RepositorioMiembro();

    return instance;
  }

  public void agregar(Miembro miembro){
    entityManager().persist(miembro);
  }

  public Miembro buscarMiembroPorID(long id) {
    return entityManager().find(Miembro.class, id);
  }

	public Miembro buscarMiembroPorDocumento(String documento) {
		return entityManager()
				.createQuery("from Miembro m where m.nroDocumento like :documento ", Miembro.class)
				.setParameter("documento", "%" + documento + "%")
				.getResultList()
				.get(0);
	}
}
