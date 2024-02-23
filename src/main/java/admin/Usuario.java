package admin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Usuario {
  @Id
  @GeneratedValue
  private Long id;
  private String userName;
  private String password;
  private String documento;
  private String rol;

  public Usuario() {
  }

  public Usuario(String userName, String password, String documento, String rol) {
    //new ValidadorRegistro().validarPassword(password);
    this.userName = userName;
    this.password = password;
    this.documento = documento;
    this.rol = rol;
  }

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }

  public String getRol() {
    return rol;
  }

  public Long getId() {
    return id;
  }

  public String getDocumento() {
    return documento;
  }
}
