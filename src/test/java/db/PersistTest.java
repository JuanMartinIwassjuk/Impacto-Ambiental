package db;

import medicionemisiones.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import motherobject.TramoMO;
import motherobject.EstacionMO;
import organizacion.*;
import recomendaciones.Contacto;
import trayecto.Trayecto;
import trayecto.Ubicacion;
import trayecto.mediodetransporte.TipoCombustible;
import trayecto.mediodetransporte.TransporteContratado;
import trayecto.mediodetransporte.TransporteParticular;
import trayecto.mediodetransporte.tipostransporte.TipoTransporteContratado;
import trayecto.mediodetransporte.tipostransporte.TipoVehiculo;
import trayecto.tramo.Tramo;
import trayecto.tramo.TramoPrivado;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PersistTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

  @Test
  public void fuenteEnergetica() {
    FuenteEnergetica fe = FuenteEnergetica.valueOf("GAS");
  }

  @BeforeEach
  public void testPersistirOperacionestosEnDb() {
    super.setup();
  }

  @AfterEach
  public void after() {
    super.tearDown();
  }

  @Test
  public void puedePersistirUnMiembro() {
    Miembro miembro = new Miembro("juan", "carlos", "25");
    entityManager().persist(miembro);
  }

  @Test
  public void puedePersistirUnTramo() {
    TramoMO trayectoMO = new TramoMO();
    entityManager().persist(trayectoMO.tramo1());
  }

  @Test
  public void puedePersistirUnTrayecto() {
    Miembro lucas = new Miembro("Lucas", "Díaz", "28610511");
    entityManager().persist(lucas);

    List<Miembro> miembros = new ArrayList<>();
    miembros.add(lucas);

    TransporteContratado uber = new TransporteContratado(TipoTransporteContratado.UBER, 2, TipoCombustible.NAFTA);
    entityManager().persist(uber);

    FactorEmision factorEmision = new FactorEmision(1224, UnidadMedida.KILOGRAMO, EquivalenciaCO2.KILOGRAMO_CO2_EQ);
    persist(factorEmision);

    TipoConsumo tipoConsumo = new TipoConsumo(FuenteEnergetica.NAFTA, factorEmision);
    persist(tipoConsumo);

    Tramo tramo = new TramoPrivado(uber, new Ubicacion("1", "Mozart", "2300"), new Ubicacion("1", "Retiro", "0"), tipoConsumo);
    entityManager().persist(tramo);

    List<Tramo> tramos = new ArrayList<>();
    tramos.add(tramo);

    Trayecto trayecto2 = new Trayecto(tramos, miembros);

    entityManager().persist(trayecto2);
  }

  @Test
  public void puedePersistirUnArea() {
    List<Miembro> miembros1 = new ArrayList<>();
    Miembro lucas = new Miembro("Lucas", "Díaz", "28610511");

    miembros1.add(lucas);
    entityManager().persist(new Area("RRHH", miembros1));
  }


  @Test
  public void puedePersistirUnaEstacion() {
    EstacionMO estacion = new EstacionMO();
    entityManager().persist(estacion.estacion1());
  }

  @Test
  public void puedePersistirUnaOrganizacion() {
    Organizacion macowins = new Organizacion("macowins", TipoOrganizacion.EMPRESA,
        new Ubicacion("Lanus", "25 de Mayo", "3480"),
        Clasificacion.EMPRESA_SECTOR_SECUNDARIO);
    entityManager().persist(macowins);

    Miembro lucas = new Miembro("Lucas", "Díaz", "28610511");
    entityManager().persist(lucas);

    List<Miembro> miembros = new ArrayList<>();
    miembros.add(lucas);
    Area area = new Area("RRHH", miembros);
    entityManager().persist(area);

    macowins.agregarArea(area);

    FactorEmision factorEmision = new FactorEmision(12, UnidadMedida.KILOGRAMO, EquivalenciaCO2.KILOGRAMO_CO2_EQ);
    entityManager().persist(factorEmision);

    TipoConsumo tipoConsumo = new TipoConsumo(FuenteEnergetica.NAFTA, factorEmision);
    entityManager().persist(tipoConsumo);

    Consumo consumo = new Consumo(tipoConsumo, 1000);
    entityManager().persist(consumo);
    macowins.addConsumo(consumo);

    Contacto contacto = new Contacto("1234263673", "jorge@gmail.com");
    entityManager().persist(contacto);

    macowins.setContacto(contacto);
    Vinculacion vinculacion1 = new Vinculacion("julian", "rodriguez", "23456765", "me caen bien", area);
    Vinculacion vinculacion2 = new Vinculacion("sergio", "manchini", "23456765", "me caen bien", area);
    entityManager().persist(vinculacion1);
    entityManager().persist(vinculacion2);
    macowins.agregarVinculacion(vinculacion1);
    macowins.agregarVinculacion(vinculacion2);

    withTransaction(() -> {
    });

    Organizacion org = entityManager()
        .createQuery("select t from Organizacion t where t.razonSocial like '%macowins%' ", Organizacion.class)
        .getResultList().get(0);

    Organizacion org2 = entityManager()
        .createQuery("select t from Organizacion t where t.tipo like \'EMPRESA\' ", Organizacion.class)
        .getResultList().get(0);
    System.out.println("la razon social 2 es");
    System.out.println(org2.getRazonSocial());

    System.out.println(org2.calcularHCTotal(Periodicidad.ANUAL));
    System.out.println(org2.calcularHCTotal(Periodicidad.MENSUAL));


    System.out.println(org.getRazonSocial());

    assertEquals(1, entityManager().createQuery("from Organizacion", Organizacion.class).getResultList().size());
  }

  @Test
  public void puedePersistirUnTransporte() {
    TransporteParticular tp = new TransporteParticular(TipoVehiculo.CAMIONETA, TipoCombustible.NAFTA, 2);
    entityManager().persist(tp);
  }


}

