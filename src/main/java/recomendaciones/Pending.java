package recomendaciones;

public class Pending {
  //Una vez implementados todos los metodos, eliminar la clase


  public static <T> T pending(String message) {
    throw new UnsupportedOperationException(message);
  }

  public static <T> T pending() {
    return pending("Not implemented yet");
  }

}
