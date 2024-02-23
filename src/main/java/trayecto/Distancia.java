package trayecto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Distancia {
  @Id
  @GeneratedValue
  private long id;

  private Double valor;

  private String unidad;

  public Distancia() {}

  public Distancia(Double valor, String unidad) {
    this.valor = valor;
    this.unidad = unidad;
  }

  public Double getValor() {
    return valor;
  }

  public String getUnidad() {
    return unidad;
  }

  @Override
  public String toString() {
    return "Distancia{"
        + "valor='"
        + valor
        + '\''
        + ", unidad='"
        + unidad
        + '\''
        + '}';
  }
}
