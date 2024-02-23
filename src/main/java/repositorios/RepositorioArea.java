package repositorios;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import organizacion.Area;
import organizacion.Organizacion;
import java.util.List;

public class RepositorioArea implements WithGlobalEntityManager {

  private static RepositorioArea instance;

  public static RepositorioArea getInstance() {
    if (instance == null)
      instance = new RepositorioArea();

    return instance;
  }

  public void agregar(Area area){
    entityManager().persist(area);
  }

  public List<Area> listar() {
    return entityManager()
      .createQuery("from Area", Area.class)
      .getResultList();
  }

  public Area buscarPorId(long id) {
    return entityManager().find(Area.class, id);
  }

  public long buscarPorNombre(String nombre) {
    return entityManager()
      .createQuery("from Area c where c.nombre like :nombre", Organizacion.class)
      .setParameter("nombre", "%" + nombre + "%") //
      .getResultList().get(0).getId();
  }
}
