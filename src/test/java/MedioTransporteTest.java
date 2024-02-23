import static org.junit.jupiter.api.Assertions.assertEquals;

import motherobject.TransporteMO;
import org.junit.jupiter.api.Test;

import motherobject.EstacionMO;
import trayecto.mediodetransporte.TipoCombustible;
import trayecto.mediodetransporte.tipostransporte.TipoTransporteContratado;
import trayecto.mediodetransporte.transportepublico.Linea;
import trayecto.mediodetransporte.transportepublico.TipoTransportePublico;
import trayecto.mediodetransporte.tipostransporte.TipoVehiculo;
import trayecto.mediodetransporte.transportepublico.TransportePublico;

public class MedioTransporteTest {
  EstacionMO estacionMO = new EstacionMO();
  TransporteMO transporteMO = new TransporteMO();

  @Test
  public void elTipoDeUnVehiculoParticularEsCamioneta() {
    assertEquals(
        (transporteMO.transporteparticular(TipoVehiculo.CAMIONETA, TipoCombustible.NAFTA)).getTipo(),
        "CAMIONETA");
  }

  @Test
  public void elTipoDeUnServicioContratadoEsAuto() {
    assertEquals(transporteMO.transportecontratado(TipoTransporteContratado.TAXI, 2, TipoCombustible.GASOIL).getTipo(), TipoTransporteContratado.TAXI);
  }

  @Test
  public void elTipoDeUnTransportePublicoEsSubte() {
    assertEquals((new TransportePublico(TipoTransportePublico.SUBTE, new Linea(estacionMO.estacionesSubteL(), estacionMO.estacionesSubteL()), TipoCombustible.ELECTRICO, 0)
        .getTipoTransportePublico().toString()), "SUBTE");
  }
}