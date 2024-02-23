package trayecto.mediodetransporte;

import trayecto.mediodetransporte.tipostransporte.TipoVehiculo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("2")
public class TransporteParticular extends MedioDeTransporte {
  @Enumerated(EnumType.STRING)
  private TipoVehiculo tipoVehiculo;

  public TransporteParticular(TipoVehiculo tipoVehiculo,
                              TipoCombustible tipoCombustible,
                              double litrosCombustiblePorKm) {
    super(litrosCombustiblePorKm, tipoCombustible);
    this.tipoVehiculo = tipoVehiculo;
  }

  public String getTipo() {
    return this.tipoVehiculo.toString();
  }

  public TipoVehiculo getTipoVehiculo() {
    return tipoVehiculo;
  }

}
