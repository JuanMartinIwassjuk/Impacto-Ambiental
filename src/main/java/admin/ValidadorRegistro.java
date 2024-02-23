package admin;

import java.util.ArrayList;
import java.util.List;

import admin.validacionesclave.ValidacionClaveListaNegra;
import admin.validacionesclave.ValidacionLongitudClave;

public class ValidadorRegistro {
  List<ValidacionClave> validadores = new ArrayList<>();

  public ValidadorRegistro() {
    validadores.add(new ValidacionClaveListaNegra());
    validadores.add(new ValidacionLongitudClave());
  }

  public void validarPassword(String password) {
    validadores.forEach(v -> v.validarClave(password));
  }
}
