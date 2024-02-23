import motherobject.RepositorioTipoConsumoMO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import organizacion.Clasificacion;
import organizacion.Organizacion;
import organizacion.TipoOrganizacion;
import sectorTerritorial.SectorTerritorial;
import sectorTerritorial.TipoSector;
import trayecto.Ubicacion;
import utils.LectorCsvConsumos;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SectorTerritorialTest {

  LectorCsvConsumos lectorCSVConsumos;
  Organizacion macowins;
  Organizacion macowins2;
  SectorTerritorial sector;
  repositorios.RepositorioTipoConsumo repositorioTipoConsumo;
  RepositorioTipoConsumoMO repoTest;

  @BeforeEach
  public void init() {
    lectorCSVConsumos = new LectorCsvConsumos(";", "consumos.csv");
    repositorioTipoConsumo = repositorios.RepositorioTipoConsumo.getRepositorioTipoConsumo();
    repoTest = new RepositorioTipoConsumoMO();
    repositorioTipoConsumo.setTiposConsumo(repoTest.tiposConsumos());

    macowins = new Organizacion("macowins", TipoOrganizacion.EMPRESA,
        new Ubicacion("Lanus", "25 de Mayo", "3480"),
      Clasificacion.EMPRESA_SECTOR_SECUNDARIO);
    macowins2 = new Organizacion("macowins2", TipoOrganizacion.EMPRESA, new Ubicacion("Palermo", "Güemes", "1290"),
      Clasificacion.EMPRESA_SECTOR_SECUNDARIO);

    sector = new SectorTerritorial(TipoSector.DEPARTAMENTOS, new ArrayList<>());
  }

  @Test
  public void seAgregaronOrganizacionesAUnSectorTerritorial() {

    sector.addOrganizacion(macowins);
    sector.addOrganizacion(macowins2);

    assertEquals(2, sector.getOrganizaciones().size());
  }

  @Test
  public void elCalculoDeLaHCEsLaSumaDeLosHCDeLasOrganizaciones() {
	  /*
    macowins.addConsumos(lectorCSVConsumos.obtenerConsumos());
    macowins2.addConsumos(lectorCSVConsumos.obtenerConsumos());

    sector.addOrganizacion(macowins);
    sector.addOrganizacion(macowins2);
    assertEquals(24403.83, sector.calcularHCTotal(), 0.001);
    */

  }



}
