package motherobject;

import services.geodds.ServiceGeodds;
import trayecto.Ubicacion;
import trayecto.mediodetransporte.*;
import trayecto.mediodetransporte.tipostransporte.TipoTransporteContratado;
import trayecto.mediodetransporte.tipostransporte.TipoVehiculo;
import trayecto.tramo.TramoPrivado;

import static org.mockito.Mockito.mock;

public class TransporteMO {
  ServiceGeodds serviceGeodds = mock(ServiceGeodds.class);

  public TransporteParticular transporteparticular(TipoVehiculo tipo, TipoCombustible combustible) {
    TransporteParticular transporteparticular = new TransporteParticular(tipo, combustible, 2);
    return transporteparticular;
  }

  public TransporteContratado transportecontratado(TipoTransporteContratado tipo, double litrosCombustiblePorKm,
                                                   TipoCombustible combustible) {
    TransporteContratado transportecontratado = new TransporteContratado(tipo, litrosCombustiblePorKm, combustible);

    return transportecontratado;
  }
}
