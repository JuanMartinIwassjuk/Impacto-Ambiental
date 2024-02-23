package motherobject;
import recomendaciones.Contacto;

public class ContactoMO {

  public Contacto contacto1() {
    return new Contacto("1234567890", "correo@mail.com");
  }

  public Contacto contacto2() {
    return new Contacto("1234567891", "correo1@mail.com");
  }
}
