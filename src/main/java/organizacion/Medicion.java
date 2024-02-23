package organizacion;

import com.opencsv.bean.CsvBindByName;

public class Medicion {
  @CsvBindByName(column = "Tipo de consumo")
  private String tipoConsumo;

  @CsvBindByName(column = "Unidad")
  private String unidad;

  @CsvBindByName(column = "Actividad")
  private String actividad;

  @CsvBindByName(column = "Alcance")
  private String alcance;

  @CsvBindByName(column = "Valor")
  private String valor;

  @CsvBindByName(column = "Periodicidad")
  private String periodicidad;

  @CsvBindByName(column = "Periodo de imputacion")
  private String periodoImputacion;

  public String getTipoConsumo() {
    return tipoConsumo;
  }

  public void setTipoConsumo(String tipoConsumo) {
    this.tipoConsumo = tipoConsumo;
  }

  public String getUnidad() {
    return unidad;
  }

  public void setUnidad(String unidad) {
    this.unidad = unidad;
  }

  public String getActividad() {
    return actividad;
  }

  public void setActividad(String actividad) {
    this.actividad = actividad;
  }

  public String getAlcance() {
    return alcance;
  }

  public void setAlcance(String alcance) {
    this.alcance = alcance;
  }

  public String getValor() {
    return valor;
  }

  public void setValor(String valor) {
    this.valor = valor;
  }

  public String getPeriodicidad() {
    return periodicidad;
  }

  public void setPeriodicidad(String periodicidad) {
    this.periodicidad = periodicidad;
  }

  public String getPeriodoImputacion() {
    return periodoImputacion;
  }

  public void setPeriodoImputacion(String periodoImputacion) {
    this.periodoImputacion = periodoImputacion;
  }

  @Override
  public String toString() {
    return "organizacion.Medicion{"
        + "tipoConsumo='"
        + tipoConsumo
        + '\''
        + ", unidad='"
        + unidad
        + '\''
        + ", actividad='"
        + actividad
        + '\''
        + ", alcance='"
        + alcance
        + '\''
        + ", valor='"
        + valor
        + '\''
        + ", periodicidad='"
        + periodicidad
        + '\''
        + ", periodoImputacion='"
        + periodoImputacion
        + '\''
        + '}';
  }
}
