package services.geodds.entities;

public class DistanciaAPI {
  String valor;
  String unidad;

  public Double getValor() {
    return Double.parseDouble(valor);
  }

  public String getUnidad() {
    return unidad;
  }

  public void setValor(String valor) {
    this.valor = valor;
  }

  public void setUnidad(String unidad) {
    this.unidad = unidad;
  }
}
