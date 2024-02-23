package motherobject;

import medicionemisiones.FactorEmision;
import medicionemisiones.FuenteEnergetica;
import medicionemisiones.TipoConsumo;
import medicionemisiones.UnidadMedida;
import organizacion.Miembro;
import services.geodds.ServiceGeodds;
import trayecto.Trayecto;
import trayecto.Ubicacion;
import trayecto.mediodetransporte.TipoCombustible;
import trayecto.mediodetransporte.TransporteContratado;
import trayecto.mediodetransporte.tipostransporte.TipoTransporteContratado;
import trayecto.tramo.Tramo;
import trayecto.tramo.TramoPrivado;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class TrayectoMO {
  ServiceGeodds serviceGeodds = mock(ServiceGeodds.class);

  TransporteContratado uber = new TransporteContratado(TipoTransporteContratado.UBER, 2, TipoCombustible.NAFTA);

  MiembroMO miembroMO = new MiembroMO();

  TransporteMO transporteMO = new TransporteMO();

  TramoMO tramoMO = new TramoMO();

  public Trayecto trayecto1() {

    List<Tramo> tramos = new ArrayList<>();
    List<Miembro> miembros = new ArrayList<>();

    Tramo tramo1 = new TramoPrivado(uber, new Ubicacion("1", "Mozart", "2300"), new Ubicacion("1", "Retiro", "0"), new TipoConsumo(FuenteEnergetica.NAFTA, new FactorEmision(123, UnidadMedida.KILOGRAMO)));

    tramos.add(tramo1);
    Tramo tramo2 = new TramoPrivado(uber, new Ubicacion("1", "Retiro", "0"), new Ubicacion("1", "Nicolas Avellaneda", "1663"), new TipoConsumo(FuenteEnergetica.NAFTA, new FactorEmision(123, UnidadMedida.KILOGRAMO)));

    miembros.add(miembroMO.miembro1());
    miembros.add(miembroMO.miembro2());

    tramos.add(tramo2);

    Trayecto trayecto2 = new Trayecto(tramos, miembros);
    return trayecto2;

  }

  public Trayecto trayecto2() {
    List<Tramo> tramos2 = new ArrayList<>();
    List<Miembro> miembros = new ArrayList<>();

    Tramo tramo3 = new TramoPrivado(uber, new Ubicacion("1", "Mozart", "2300"), new Ubicacion("1", "3 de febrero", "554"), new TipoConsumo(FuenteEnergetica.NAFTA, new FactorEmision(123, UnidadMedida.KILOGRAMO)));
    tramos2.add(tramo3);
    Tramo tramo4 = new TramoPrivado(uber, new Ubicacion("1", "3 de febrero", "554"), new Ubicacion("1", "Avenida Centenario", "100"), new TipoConsumo(FuenteEnergetica.NAFTA, new FactorEmision(123, UnidadMedida.KILOGRAMO)));

    miembros.add(miembroMO.miembro2());
    miembros.add(miembroMO.miembro3());

    tramos2.add(tramo4);

    Trayecto trayecto3 = new Trayecto(tramos2, miembros);
    return trayecto3;

  }

  public Trayecto trayecto3() {
    List<Tramo> tramos = new ArrayList<>();
    List<Miembro> miembros = new ArrayList<>();

    Tramo tramo1 = tramoMO.tramo((transporteMO.transportecontratado(TipoTransporteContratado.UBER, 2, TipoCombustible.NAFTA)), new Ubicacion("1", "Mozart", "2300"), new Ubicacion("1", "Retiro", "0"));
    tramos.add(tramo1);
    Tramo tramo2 = tramoMO.tramo((transporteMO.transportecontratado(TipoTransporteContratado.UBER, 2, TipoCombustible.NAFTA)), new Ubicacion("1", "Retiro", "0"), new Ubicacion("1", "Nicolas Avellaneda", "1663"));
    tramos.add(tramo2);
    Tramo tramo3 = tramoMO.tramo((transporteMO.transportecontratado(TipoTransporteContratado.UBER, 2, TipoCombustible.NAFTA)), new Ubicacion("1", "Nicolas Avellaneda", "1663"),
        new Ubicacion("1", "3 de febrero", "554"));

    miembros.add(miembroMO.miembro1());
    miembros.add(miembroMO.miembro3());

    tramos.add(tramo3);

    Trayecto trayecto = new Trayecto(tramos, miembros);
    return trayecto;
  }

  public List<Trayecto> trayectos() {
    List<Trayecto> trayectos = new ArrayList<>();
    trayectos.add(this.trayecto1());
    trayectos.add(this.trayecto2());
    return trayectos;
  }

}
