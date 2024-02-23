package motherobject;

import organizacion.Miembro;

public class MiembroMO {
  ConsumoMO consumoMO = new ConsumoMO();
  public Miembro miembro1() {
    Miembro miembro = new Miembro("José", "Hernández", "24665312");
    miembro.agregarConsumo(consumoMO.consumo1());
    miembro.agregarConsumo(consumoMO.consumo4());
    return miembro;
  }

  public Miembro miembro2() {
    Miembro miembro = new Miembro("Alan", "Díaz", "28610511");
    miembro.agregarConsumo(consumoMO.consumo2());
    return miembro;
  }

  public Miembro miembro3() {
    Miembro miembro = new Miembro("Ricardo", "Molina", "31658471");
    miembro.agregarConsumo(consumoMO.consumo3());
    return miembro;
  }

}
