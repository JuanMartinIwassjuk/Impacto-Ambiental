package trayecto.mediodetransporte.transportepublico;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import trayecto.Distancia;
@Entity
public class Linea {
  @Id
  @GeneratedValue
  private long id;

  @OneToMany
  @JoinColumn(name = "linea_id")
  private List<Estacion> estacionesIda;

  @OneToMany
  @JoinColumn(name = "linea_id")
  private List<Estacion> estacionesVuelta;

  public Linea(List<Estacion> estacionesIda, List<Estacion> estacionesVuelta) {
    this.estacionesIda = estacionesIda;
    this.estacionesVuelta = estacionesVuelta;
  }

  public List<Estacion> getEstacionIda() {
    return estacionesIda;
  }

  public List<Estacion> getEstacionVuelta() {
    return estacionesVuelta;
  }


  public void addEstacionIda(int orden, Estacion estacion) {
    estacionesIda.add(orden, estacion);
  }

  public void addEstacionVuelta(int orden, Estacion estacion) {
    estacionesVuelta.add(orden, estacion);
  }

  public Distancia distancia(Estacion origen, Estacion destino) {
    List<Estacion> estaciones = elegirRecorrido(origen, destino);

    return new Distancia(this.calularDistancia(estaciones), "KM");
  }

  public double calularDistancia(
      List<Estacion> estaciones) {
    return estaciones
        .subList(0,estaciones.size()-1)
        .stream()
        .mapToDouble(estacion -> estacion.distanciaASiguienteEstacion()
            .getValor()).sum();
  }

  public List<Estacion> elegirRecorrido(Estacion origen, Estacion destino) {
    List<List<Estacion>> recorridos = new ArrayList<>();
    recorridos.add(estacionesIda);
    recorridos.add(estacionesVuelta);
    return ValidadorLinea.elegirRecorrido(recorridos, origen, destino);
  }

}

 
