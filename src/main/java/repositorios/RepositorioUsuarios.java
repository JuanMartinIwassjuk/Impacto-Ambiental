package repositorios;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import admin.Usuario;

public class RepositorioUsuarios implements WithGlobalEntityManager {

  private static RepositorioUsuarios instance;

  public static RepositorioUsuarios getInstance() {
    if (instance == null)
      instance = new RepositorioUsuarios();

    return instance;
  }

  public List<Usuario> getUsuarios() {
    return entityManager()
        .createQuery("from Usuario", Usuario.class)
        .getResultList();
  }

  public Usuario buscarPorUsuarioYcontrasenia(String username, String password) {
    return this.getUsuarios().stream().filter(u -> u.getPassword().equals(password) && u.getUserName().equals(username)).findFirst().get();
  }

  public void agregar(Usuario usuario) {
    entityManager().persist(usuario);
  }
}
