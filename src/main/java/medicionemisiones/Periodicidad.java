package medicionemisiones;

public enum Periodicidad {
  ANUAL("A"),
  MENSUAL("M");

  private String codigo;

  Periodicidad(String codigo) {
    this.codigo = codigo;
  }

  public String getCodigo() {
    return this.codigo;
  }
  

}
