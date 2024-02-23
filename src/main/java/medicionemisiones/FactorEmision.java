package medicionemisiones;

import javax.persistence.*;

@Entity
public class FactorEmision {
  @Id
  @GeneratedValue
  private long id;

  private double valor;

  @Enumerated(EnumType.STRING)
  private UnidadMedida unidad;


  @Enumerated(EnumType.STRING)
  private EquivalenciaCO2 equivalenciaCO2;
  
  public FactorEmision() {}

  public FactorEmision(double valor, UnidadMedida unidad) {
    this.valor = valor;
    this.unidad = unidad;
  }

  public FactorEmision(double valor, UnidadMedida unidad, EquivalenciaCO2 equivalenciaCO2) {
    this.valor = valor;
    this.unidad = unidad;
    this.equivalenciaCO2 = equivalenciaCO2;
  }

  public double getValor() {
    return valor;
  }

  public UnidadMedida getUnidad() {
    return unidad;
  }

  public EquivalenciaCO2 getEquivalenciaCO2() {
    return equivalenciaCO2;
  }

  public void setEquivalenciaCO2(EquivalenciaCO2 equivalenciaCO2) {
    this.equivalenciaCO2 = equivalenciaCO2;
  }

  public void setValor(double valor){
    this.valor = valor;
  }

  public double calcularHCEnGramosCO2(double consumo) {
    return equivalenciaCO2.valorEnGramos(valor * consumo);
  }

  public double calcularHCEnKilogramosCO2(double consumo) {
    return equivalenciaCO2.valorEnKilogramos(consumo * valor);
  }

  public double calcularHCEnToneladasCO2(double consumo) {
    return equivalenciaCO2.valorEnToneladas(consumo * valor);
  }
}
