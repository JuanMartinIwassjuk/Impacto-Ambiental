package medicionemisiones;

public class HuellaDeCarbono {

  private double consumo;

  private TipoConsumo tipoConsumo;

  public HuellaDeCarbono(double consumo, TipoConsumo tipoConsumo) {
    this.consumo = consumo;
    this.tipoConsumo = tipoConsumo;
  }
  
  public double calcular(Calculador calculador) {
	  return 0;
  }

  public double enGramosCO2() {
    return this.tipoConsumo.getFactorEmision().calcularHCEnGramosCO2(consumo);
  }

  public double enKilogramosCO2() {
    return this.tipoConsumo.getFactorEmision().calcularHCEnKilogramosCO2(consumo);
  }

  public double enToneladasCO2() {
    return this.tipoConsumo.getFactorEmision().calcularHCEnToneladasCO2(consumo);
  }
  
  public double getConsumo() {
	  return consumo;
  }
  
  public TipoConsumo getTipoConsumo() {
	  return tipoConsumo;
  }
}
