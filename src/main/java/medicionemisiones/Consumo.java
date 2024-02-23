package medicionemisiones;

import javax.persistence.*;

@Entity
public class Consumo {
  @Id
  @GeneratedValue
  private long id;

  @ManyToOne
  private TipoConsumo tipoConsumo;

  @Enumerated(EnumType.STRING)
  private Periodicidad periodicidad;

  private double valor;

  private Integer mes;
  private Integer anio;
  
  private String fe;
  private String unidad;
  private String periodicidads;
  

  public Consumo() {
  }

  public Consumo(TipoConsumo tipoConsumo, double valor) {
    this.tipoConsumo = tipoConsumo;
    this.valor = valor;
    this.periodicidad = Periodicidad.MENSUAL;
    this.fe = tipoConsumo.getFuenteEnergetica().toString();
    this.unidad = tipoConsumo.getFactorEmision().getUnidad().toString();
    this.periodicidads=periodicidad.toString();
  }

  public Consumo(TipoConsumo tipoConsumo, Periodicidad periodicidad, double valor) {
    this.tipoConsumo = tipoConsumo;
    this.periodicidad = periodicidad;
    this.valor = valor;
    this.fe = tipoConsumo.getFuenteEnergetica().toString();
    this.unidad = tipoConsumo.getFactorEmision().getUnidad().toString();
    this.periodicidads=periodicidad.toString();
  }

  public Consumo(TipoConsumo tipoConsumo,
                 Periodicidad periodicidad,
                 double valor,
                 Integer mes,
                 Integer anio) {
    this.tipoConsumo = tipoConsumo;
    this.periodicidad = periodicidad;
    this.valor = valor;
    this.mes = mes;
    this.anio = anio;
    this.fe = tipoConsumo.getFuenteEnergetica().toString();
    this.unidad = tipoConsumo.getFactorEmision().getUnidad().toString();
    this.periodicidads=periodicidad.toString();
  }
//a
  public double getValor() {
    return valor;
  }

  public TipoConsumo getTipoConsumo() {
    return tipoConsumo;
  }

  public Periodicidad getPeriodicidad() {
    return periodicidad;
  }

  public Integer getMes() {
    return mes;
  }

  public Integer getAnio() {
    return anio;
  }

  public HuellaDeCarbono calcularHC(){
    return new HuellaDeCarbono(valor, tipoConsumo);
  }

public String getFe() {
	return fe;
}

public String getUnidad() {
	return unidad;
}

public String getPeriodicidads() {
	return periodicidads;
}
  
 
}
