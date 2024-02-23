package organizacion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Recomendacion {
	
  @Id
  @GeneratedValue
  private long id;
  
  private String titulo;
  
  private String detalle;
  
  public Recomendacion() {
  }

  public Recomendacion(String titulo, String detalle) {
    this.titulo = titulo;
    this.detalle = detalle;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getDetalle() {
    return detalle;
  }

  public void setDetalle(String detalle) {
    this.detalle = detalle;
  }
}
