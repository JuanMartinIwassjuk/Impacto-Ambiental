package admin;

import java.util.ArrayList;
import java.util.List;

public class Registro {

  private List<Usuario> administradores = new ArrayList<>();

  public void registrarAdministrador(Usuario administrador) {
    administradores.add(administrador);
  }

  public List<Usuario> getAdministradores() {
    return administradores;
  }

}
