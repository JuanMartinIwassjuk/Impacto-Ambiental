package medicionemisiones;

import javax.persistence.*;

@Entity
public class TipoConsumo {

  @Id
  @GeneratedValue
  private long id;

  @Enumerated(EnumType.STRING)
  private FuenteEnergetica fuenteEnergetica;

  @ManyToOne
  private FactorEmision factorEmision;
  

  
 public TipoConsumo() {}

  public TipoConsumo(FuenteEnergetica fuenteEnergetica,
                     FactorEmision factorEmision) {
    this.fuenteEnergetica = fuenteEnergetica;
    this.factorEmision = factorEmision;
  }

  public FactorEmision getFactorEmision() {
    return factorEmision;
  }

  public FuenteEnergetica getFuenteEnergetica() {
    return fuenteEnergetica;
  }
  

}
