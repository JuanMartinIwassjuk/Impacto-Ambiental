package exceptions;

public class NoSePudoEnviarEmail extends RuntimeException {
  public NoSePudoEnviarEmail(String message) {
    super(message);
  }
}
