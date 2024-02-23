package recomendaciones;

import organizacion.Organizacion;

import java.util.ArrayList;
import java.util.List;

public class ListaDeEnvios {
  List<InteresadosEnGuia> interesados = new ArrayList<>();

  public void agregarInteresadosAlEvento(InteresadosEnGuia unInteresado) {
    interesados.add(unInteresado);
  }

  public void enviarNotificacionA(String link, Organizacion organizacion) {
    interesados.forEach(interesado -> interesado.notificarTiempoCumplido(link, organizacion.getContacto()));
  }
}
