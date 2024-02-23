import java.io.IOException;
import exceptions.CaminoInexistenteException;
import org.junit.jupiter.api.Test;

import motherobject.*;

import static org.junit.jupiter.api.Assertions.*;

public class TransportePublicoTest {
  EstacionMO motherObj = new EstacionMO();

  @Test
  public void unaEstacionTieneNombre() {
    assertEquals("San Pedrito", motherObj.estacion1().getNombre());

  }

  @Test
  public void unaEstacionTieneUbicacionConLocalidadCalleyAltura() {
    assertEquals("1", motherObj.estacion1().getUbicacion().getLocalidad());
    assertEquals("Av. San Pedrito", motherObj.estacion1().getUbicacion().getCalle());
    assertEquals("1300", motherObj.estacion1().getUbicacion().getAltura());

  }

  @Test
  public void unaEstacionTieneUnaDistanciaASiguienteEstacion() {

    assertEquals(3.0, motherObj.estacion1().distanciaASiguienteEstacion().getValor());
    assertEquals("KM", motherObj.estacion1().distanciaASiguienteEstacion().getUnidad());

  }

  @Test
  public void sePuedeCalcularLaDistanciaEntre2EstacionesDeUnaLinea() throws IOException {

    assertEquals((double) 6, motherObj.linea1().distancia(motherObj.estacion1(), motherObj.estacion3()).getValor());

  }

  @Test
  public void noSePuedeCalcularLaDistanciaEntre2EstacionesDeUnaLineaDiferente() throws IOException {

    assertThrows(CaminoInexistenteException.class, () -> motherObj.linea1().distancia(motherObj.estacion1(), motherObj.estacion4()).getValor());

  }

}
