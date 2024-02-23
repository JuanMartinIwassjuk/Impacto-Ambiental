package trayecto.mediodetransporte.transportepublico;

import exceptions.CaminoInexistenteException;
import exceptions.EstacionNoPerteneceException;

import java.util.List;
import java.util.stream.Collectors;

public final class ValidadorLinea {

  private static boolean validarRecorrido(List<Estacion> estaciones, Estacion est1, Estacion est2) {
    boolean is1inList = estaciones.contains(est1);
    boolean is2inList = estaciones.contains(est2);
    boolean is1before2 = estaciones.indexOf(est1) < estaciones.indexOf(est2);

    return is1inList && is2inList && is1before2;
  }

  public static List<Estacion> elegirRecorrido(List<List<Estacion>> recorridos,
                                               Estacion origen, Estacion destino) {
    List<List<Estacion>> recorridosValidos = recorridos.stream()
        .filter(recorrido -> validarRecorrido(recorrido, origen, destino))
        .collect(Collectors.toList());
    if (recorridosValidos.size() > 0) {
      return recorridosValidos.get(0);
    } else {
      throw new CaminoInexistenteException(
          "Las estaciones ingresadas no representan un camino en esta linea"
      );
    }
  }

  public static Boolean iguales(Estacion est1, Estacion est2) {
    if (est1 == null || est2 == null) {
      return false;
    }

    return ((est1.getNombre() == null && est2.getNombre() == null)
            ||
        (est1.getNombre().equals(est2.getNombre())))
        &&
        ((est1.getUbicacion().getAltura() == null && est2.getUbicacion().getAltura() == null)
            ||
            est1.getUbicacion().getAltura().equals(est2.getUbicacion().getAltura()))
        &&
        ((est1.getUbicacion().getCalle() == null && est2.getUbicacion().getCalle() == null)
            ||
            est1.getUbicacion().getCalle().equals(est2.getUbicacion().getCalle()))
        &&
        ((est1.getUbicacion().getLocalidad() == null && est2.getUbicacion().getLocalidad() == null)
            ||
            est1.getUbicacion().getLocalidad().equals(est2.getUbicacion().getLocalidad()))
        &&
        ((est1.getUbicacion().getDireccion() == null && est2.getUbicacion().getDireccion() == null)
            ||
            est1.getUbicacion().getDireccion().equals(est2.getUbicacion().getDireccion()));

  }

}
