package organizacion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vinculacion {

  @Id
  @GeneratedValue
  private long id;

  @ManyToOne
  private Area area;

  private String nombre;

  private String apellido;

  private String nroDocumento;

  private String motivo;

  public Vinculacion() {
  }


  public Vinculacion(String nombre, String apellido, String nroDocumento, String motivo, Area area) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.nroDocumento = nroDocumento;
    this.motivo = motivo;
    this.area = area;
  }

  public long getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public String getNroDocumento() {
    return nroDocumento;
  }

  public String getMotivo() {
    return motivo;
  }

}
