import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import organizacion.Area;
import organizacion.Miembro;

public class AreaYMiembrosTest {

  @Test
  public void unMiembroTieneUnNombreApellidoYdocumento() {

    Miembro miembro1 = new Miembro("juan", "perez", "43600421");
    assertEquals(miembro1.getNombre(), "juan");
    assertEquals(miembro1.getApellido(), "perez");
    assertEquals(miembro1.getNroDocumento(), "43600421");

  }

  @Test
  public void puedoAgregarMiembrosAunArea() {
    Miembro miembro = new Miembro("juan", "perez", "43600421");
    List<Miembro> miembros = new ArrayList<>();
    miembros.add(miembro);

    Area areaVentas = new Area("ventas", miembros);
    Miembro miembro2 = new Miembro("jose", "antonio", "43600421");
    areaVentas.agregarMiembro(miembro2);
    assertEquals(areaVentas.getMiembros().size(), 2);
  }

}
