package trayecto.mediodetransporte;

import trayecto.mediodetransporte.tipostransporte.TipoVehiculoTas;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Entity
@DiscriminatorValue("3")
public class TransporteTas extends MedioDeTransporte { // TAS == Tracción A Sangre
  @Enumerated(EnumType.STRING)
  private TipoVehiculoTas tipoVehiculoTAS;

  public TransporteTas(TipoVehiculoTas tipoVehiculoTAS) {
    super(0,null);
    this.tipoVehiculoTAS = tipoVehiculoTAS;
  }

  public TipoVehiculoTas getTipo() {
    return this.tipoVehiculoTAS;
  }

}
