package motherobject;

import trayecto.Distancia;
import trayecto.Ubicacion;
import trayecto.mediodetransporte.transportepublico.Estacion;
import trayecto.mediodetransporte.transportepublico.Linea;

import java.util.Arrays;
import java.util.List;

public class EstacionMO {
  public List<Estacion> estacionesSubteL() {
    return Arrays.asList(

        this.estacion1(),
        this.estacion2(),
        this.estacion3()

    );
  }


  public Linea linea1() {
    return new Linea(this.estacionesSubteL(), this.estacionesSubteL());
  }


  public Estacion estacion1() {
    return new Estacion("San Pedrito", new Ubicacion("1", "Av. San Pedrito", "1300"), this.unaDistancia());

  }

  public Estacion estacion2() {
    return new Estacion("Avellaneda", new Ubicacion("1", "Av. Avellaneda", "2500"), this.unaDistancia());

  }

  public Estacion estacion3() {
    return new Estacion("San Pedrito", new Ubicacion("1", "Av. San Pedrito", "3560"), this.unaDistancia());

  }

  public Estacion estacion4() {
    return new Estacion("San Isidro", new Ubicacion("1", "3 de febrero", "554"), this.unaDistancia());

  }


  public Distancia unaDistancia() {
    return new Distancia(3.0, "KM");
  }

}
