package motherobject;

import medicionemisiones.FactorEmision;
import medicionemisiones.FuenteEnergetica;
import medicionemisiones.TipoConsumo;
import medicionemisiones.UnidadMedida;
import services.geodds.ServiceGeodds;
import trayecto.Distancia;
import trayecto.Ubicacion;
import trayecto.mediodetransporte.MedioDeTransporte;
import trayecto.mediodetransporte.TipoCombustible;
import trayecto.mediodetransporte.tipostransporte.TipoTransporteContratado;
import trayecto.mediodetransporte.TransporteParticular;
import trayecto.mediodetransporte.transportepublico.Estacion;
import trayecto.mediodetransporte.transportepublico.Linea;
import trayecto.mediodetransporte.transportepublico.TipoTransportePublico;
import trayecto.mediodetransporte.transportepublico.TransportePublico;
import trayecto.tramo.Tramo;
import trayecto.tramo.TramoPrivado;
import trayecto.tramo.TramoPublico;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class TramoMO {
  ServiceGeodds serviceGeodds = mock(ServiceGeodds.class);


  EstacionMO estacionMo = new EstacionMO();
  TransporteMO transporteMO = new TransporteMO();

  public Tramo tramo(MedioDeTransporte medioDeTransporte, Ubicacion origen, Ubicacion destino) {
    Tramo tramo = new TramoPrivado(medioDeTransporte, origen, destino, new TipoConsumo(FuenteEnergetica.NAFTA, new FactorEmision(123, UnidadMedida.KILOGRAMO)));

    return tramo;
  }

  public TramoPrivado tramo1() {
    return new TramoPrivado(
        (transporteMO.transportecontratado(TipoTransporteContratado.CABIFY, 2, TipoCombustible.NAFTA))
        , new Ubicacion("1", "Mozart", "2300")
        , new Ubicacion("1", "Guardia Vieja", "300"),
        new TipoConsumo(FuenteEnergetica.NAFTA, new FactorEmision(123, UnidadMedida.KILOGRAMO)));
  }

  public Tramo unTramoPrivado() {
    Ubicacion ubic1 = new Ubicacion("1", "25 de mayo", "1256");
    Ubicacion ubic2 = new Ubicacion("3", "Rivadavia", "2059");

    Distancia response = new Distancia(40.821, "KM");
    TransporteParticular transporteParticularMock = mock(TransporteParticular.class);
    when(transporteParticularMock.distancia(any(Ubicacion.class),any(Ubicacion.class))).thenReturn(response);

    return new TramoPrivado(transporteParticularMock, ubic1, ubic2, new TipoConsumo(FuenteEnergetica.NAFTA, new FactorEmision(123, UnidadMedida.KILOGRAMO)));
  }

  public Tramo unTramoPublico() {
    List<Estacion> estacionesTestIda = new ArrayList<>();
    estacionesTestIda.add(estacionMo.estacion1());
    estacionesTestIda.add(estacionMo.estacion2());
    estacionesTestIda.add(estacionMo.estacion4());

    List<Estacion> estacionesTestVuelta = new ArrayList<>();
    estacionesTestVuelta.add(estacionMo.estacion4());
    estacionesTestVuelta.add(estacionMo.estacion2());
    estacionesTestVuelta.add(estacionMo.estacion1());

    Linea lineaTest = new Linea(estacionesTestIda, estacionesTestVuelta);

    TransportePublico transportePublico = new TransportePublico(TipoTransportePublico.COLECTIVO, lineaTest, TipoCombustible.NAFTA, 1);

    return new TramoPublico(transportePublico, estacionMo.estacion1(), estacionMo.estacion4(), new TipoConsumo(FuenteEnergetica.NAFTA, new FactorEmision(123, UnidadMedida.KILOGRAMO)));
  }
}
