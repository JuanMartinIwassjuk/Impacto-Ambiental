package trayecto.mediodetransporte.transportepublico;

import javax.persistence.*;

import trayecto.Distancia;
import trayecto.Ubicacion;


@Entity
public class Estacion {
  @Id
  @GeneratedValue
  private long id;

  private String nombre;

  @Embedded
  private Ubicacion ubicacion;

  @OneToOne
  private Distancia distanciaSig;

  public Estacion(String nombre, Ubicacion ubicacion, Distancia distanciaSig) {
    this.nombre = nombre;
    this.ubicacion = ubicacion;
    this.distanciaSig = distanciaSig;
  }

  public String getNombre() {
    return nombre;
  }

  public Ubicacion getUbicacion() {
    return ubicacion;
  }

  public Distancia distanciaASiguienteEstacion() {
    return distanciaSig;
  }

  public void setDistanciaSig(Distancia distanciaSig) {
    this.distanciaSig = distanciaSig;
  }

  @Override
  public boolean equals(Object obj) {
    return ValidadorLinea.iguales(this, (Estacion) obj);
  }
}