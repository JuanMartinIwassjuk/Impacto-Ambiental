package trayecto.mediodetransporte;

import trayecto.mediodetransporte.tipostransporte.TipoTransporteContratado;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("1")
public class TransporteContratado extends MedioDeTransporte {

  @Enumerated(EnumType.STRING)
  private TipoTransporteContratado tipoTransporteContratado;

  public TransporteContratado(TipoTransporteContratado tipoTransporteContratado,
                              double litrosCombustiblePorKm,
                              TipoCombustible tipoCombustible) {
    super(litrosCombustiblePorKm, tipoCombustible);
    this.tipoTransporteContratado = tipoTransporteContratado;
  }

  public TipoTransporteContratado getTipo() {
    return tipoTransporteContratado;
  }
}
