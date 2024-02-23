package admin.validacionesclave;
import admin.ValidacionClave;
import exceptions.UsuarioInvalidoException;

public class ValidacionLongitudClave implements ValidacionClave {
  private static final int LARGO_CLAVE_MINIMO = 8;

  @Override
  public void validarClave(String clave) {
    if ((clave.length() < LARGO_CLAVE_MINIMO)) {
      throw new UsuarioInvalidoException(
          "La contraseña debe tener una longitudad minima de ocho caracteres");
    }
  }
}
