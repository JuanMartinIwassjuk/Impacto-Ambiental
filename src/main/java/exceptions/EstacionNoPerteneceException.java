package exceptions;

public class EstacionNoPerteneceException extends RuntimeException {
  public EstacionNoPerteneceException(String message){
    super(message);
  }
}
