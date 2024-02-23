package medicionemisiones;

public enum FuenteEnergetica {
  GAS("Gas"),
  GASOIL("Gasoil"),
  NAFTA("Nafta"),
  CARBON("Carbon"),
  GASOIL_CONSUMIDO("Gasoil Consumido"),
  NAFTA_CONSUMIDA("Nafta Consumida"),
  ELECTRICIDAD("Electricidad"),
  TRANSPORTE("Transporte"),
  DISTANCIA("Distancia");

  private String denominacion;

  FuenteEnergetica(String denominacion) {
    this.denominacion = denominacion;
  }

  public String getDenominacion() {
    return this.denominacion;
  }
}
