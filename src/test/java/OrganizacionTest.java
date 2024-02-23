import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import motherobject.ContactoMO;
import org.junit.jupiter.api.Test;

import organizacion.Organizacion;
import organizacion.TipoOrganizacion;
import organizacion.Clasificacion;
import motherobject.EstacionMO;
import trayecto.Ubicacion;

public class OrganizacionTest {
	
  EstacionMO motherObj = new EstacionMO();
  ContactoMO contactoMO = new ContactoMO();

  Organizacion macowins = new Organizacion("macowins", TipoOrganizacion.EMPRESA,
      new Ubicacion("Lanus", "25 de Mayo", "3480"),
      Clasificacion.EMPRESA_SECTOR_SECUNDARIO);

  @Test
  public void unaOrganizacionTieneUnaRazonSocial() {
    assertFalse((macowins.getRazonSocial().isEmpty()));
  }

  @Test
  public void unaOrganizacionConUnTipoEMPRESA() {
    assertEquals(macowins.getTipo().toString(), "EMPRESA");
  }

  @Test
  public void unaOrganizacionTieneUnaUbicacion() {
    assertFalse(isNull(macowins.getUbicacion()));
  }

  @Test
  public void laClasificacicionDeUnaOrganizacionDeSectorSecundarioEs() {
    assertEquals(macowins.getClasificacion().toString(), "EMPRESA_SECTOR_SECUNDARIO");
  }

}
