import medicionemisiones.Consumo;
import medicionemisiones.FuenteEnergetica;
import medicionemisiones.Periodicidad;
import medicionemisiones.TipoConsumo;
import motherobject.RepositorioTipoConsumoMO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import organizacion.Clasificacion;
import organizacion.Organizacion;
import organizacion.TipoOrganizacion;
import trayecto.Ubicacion;
import utils.LectorCsvConsumos;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConsumosTest {
  LectorCsvConsumos lectorCSVConsumos;
  repositorios.RepositorioTipoConsumo repositorioTipoConsumo;
  RepositorioTipoConsumoMO repoTest;
  Organizacion organizacionTest;

  @BeforeEach
  public void init() {
    lectorCSVConsumos = new LectorCsvConsumos(";", "consumos.csv");
    repositorioTipoConsumo = repositorios.RepositorioTipoConsumo.getRepositorioTipoConsumo();
    repoTest = new RepositorioTipoConsumoMO();
    organizacionTest = new Organizacion("Test", TipoOrganizacion.EMPRESA, new Ubicacion("CABA","9 de Julio", "2340"), Clasificacion.EMPRESA_SECTOR_PRIMARIO);
    repositorioTipoConsumo.setTiposConsumo(repoTest.tiposConsumos());
  }

  @Test
  public void testPuedoProcesarArchivoCSVCon8Consumos() {
    assertEquals(8, lectorCSVConsumos.obtenerConsumos().size());
  }

  @Test
  public void testPuedoAgregarConsumosAOrganizacion() {
    organizacionTest.addConsumos(lectorCSVConsumos.obtenerConsumos());
    assertEquals(8, organizacionTest.getConsumosPropios().size());
  }

  @Test
  public void seCargo200DeValorConsumoDeNaftaDeFormaMensual() {
    List<Consumo> consumos = lectorCSVConsumos.obtenerConsumos();
    TipoConsumo tipoConsumo = consumos.get(0).getTipoConsumo();
    assertEquals(200, consumos.get(0).getValor());
    assertEquals(FuenteEnergetica.NAFTA,tipoConsumo.getFuenteEnergetica());
    assertEquals(Periodicidad.MENSUAL, consumos.get(0).getPeriodicidad());
  }

  @Test
  public void laTercerCargaEsDeJunioDe2022() {
    List<Consumo> consumos = lectorCSVConsumos.obtenerConsumos();
    Consumo consumo = consumos.get(2);
    assertEquals(6, consumo.getMes());
    assertEquals(2022, consumo.getAnio());
  }

  @Test
  public void laCuartaCargaEsSinMesYDe2022() {
    List<Consumo> consumos = lectorCSVConsumos.obtenerConsumos();
    Consumo consumo = consumos.get(3);
    assertNull(consumo.getMes());
    assertEquals(2022, consumo.getAnio());
  }




}
