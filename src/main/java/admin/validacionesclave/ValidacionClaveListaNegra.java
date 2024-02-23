package admin.validacionesclave;
import admin.ValidacionClave;
import exceptions.UsuarioInvalidoException;
import utils.ReadTextAsString;

public class ValidacionClaveListaNegra implements ValidacionClave {
  @Override
  public void validarClave(String clave) {
    if (ReadTextAsString.readFileAsString(
        "top_10000_worst_passwords.txt").contains(clave)) {
      throw new UsuarioInvalidoException(
          "Eligio una de las contraseñas mas usadas. Por favor ingrese otra");
    }
  }
}
