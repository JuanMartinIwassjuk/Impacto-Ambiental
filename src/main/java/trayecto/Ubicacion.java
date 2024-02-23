package trayecto;

import javax.persistence.Embeddable;

@Embeddable
public class Ubicacion {
  private String localidad;
  private String calle;
  private String altura;

  public Ubicacion() {
  }

  public Ubicacion(String localidad, String calle, String altura) {
    this.localidad = localidad;
    this.calle = calle;
    this.altura = altura;
  }

  public String getLocalidad() {
    return localidad;
  }

  public String getCalle() {
    return calle;
  }

  public String getAltura() {
    return altura;
  }

  public String getDireccion() {
    return this.calle + " " + altura;
  }
}
