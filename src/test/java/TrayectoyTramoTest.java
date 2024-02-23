import motherobject.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

import trayecto.Trayecto;
import trayecto.mediodetransporte.TipoCombustible;
import trayecto.mediodetransporte.tipostransporte.TipoTransporteContratado;
import trayecto.tramo.Tramo;


public class TrayectoyTramoTest {

  MiembroMO miembroMO = new MiembroMO();
  TrayectoMO trayectoMO = new TrayectoMO();

  TransporteMO transporteMO = new TransporteMO();

  TramoMO tramoMO = new TramoMO();

  @Test
  public void elMedioDeTransporteDeUnTramoEs() {
    assertEquals(TipoTransporteContratado.CABIFY, transporteMO.transportecontratado(TipoTransporteContratado.CABIFY, 2, TipoCombustible.NAFTA).getTipo());
  }

  @Test
  public void elOrgienDeUnTramoEs() {
    assertEquals(tramoMO.tramo1().getUbicOrigen().getCalle(), "Mozart");
    assertEquals(tramoMO.tramo1().getUbicOrigen().getAltura(), "2300");
  }

  @Test
  public void elDestinoDeUnTramoEs() {

    assertEquals(tramoMO.tramo1().getUbicDestino().getCalle(), "Guardia Vieja");
    assertEquals(tramoMO.tramo1().getUbicDestino().getAltura(), "300");
  }

  @Test
  public void puedoAgregarUnTramo() {

    assertEquals(trayectoMO.trayecto3().getTramos().size(), 3);
  }

  @Test
  public void elOrigenDeUnTrayectoEs() {
    assertEquals(trayectoMO.trayecto3().getOrigen().getDireccion(), "Mozart 2300");
  }

  @Test
  public void elDestinoDeUnTrayectoEs() {
    assertEquals(trayectoMO.trayecto3().getDestino().getDireccion(), "3 de febrero 554");
  }

  @Test
  public void sePuedeCalcularLaDistanciaDeUnTramoPrivado(){
    assertEquals(40.821, tramoMO.unTramoPrivado().distancia());
  }

  @Test
  public void sePuedeCalcularLaDistanciaDeUnTramoPublico(){
    assertEquals(6, tramoMO.unTramoPublico().distancia());
  }

  @Test
  public void laDistanciaDeUnTrayectoEsLaSumaDeLosTramos(){
    List<Tramo> tramos = new ArrayList<>();
    tramos.add(tramoMO.unTramoPublico());
    tramos.add(tramoMO.unTramoPrivado());

    Trayecto trayecto = new Trayecto(tramos, new ArrayList<>());
    assertEquals(46.821, trayecto.distancia().getValor());
  }

  @Test
  public void sePuedenCrearTrayectosCompartidos(){
    Trayecto trayecto = new Trayecto(new ArrayList<>(), new ArrayList<>());
    trayecto.agregarIntegrante(miembroMO.miembro2());
    trayecto.agregarIntegrante(miembroMO.miembro1());

    assertEquals(2, trayecto.getIntegrantes().size());
  }

}