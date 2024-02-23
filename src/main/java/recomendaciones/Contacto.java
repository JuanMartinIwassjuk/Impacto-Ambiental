package recomendaciones;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Contacto {
  @Id
  @GeneratedValue
  private long id;
  private String numeroTelefono;
  private String email;
  
  public Contacto() {
	this.numeroTelefono = "";
	this.email = "";
  }

  public Contacto(String numeroTelefono, String email) {
    this.numeroTelefono = numeroTelefono;
    this.email = email;
  }

  public String getNumeroTelefono() {
    return numeroTelefono;
  }

  
  public String getEmail() {
    return email;
  }

}
