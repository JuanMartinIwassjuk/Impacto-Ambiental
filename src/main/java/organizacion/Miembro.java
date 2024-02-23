package organizacion;

import medicionemisiones.Consumo;
import medicionemisiones.HuellaDeCarbono;
import medicionemisiones.Periodicidad;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Miembro {
  @Id
  @GeneratedValue
  private long id;

  private String nombre;

  private String apellido;

  private String nroDocumento;

  @OneToMany
  @JoinColumn(name = "miembro_id")
  private List<Consumo> consumos = new ArrayList<>();

  public Miembro() {
  }
  
  public Miembro(String nombre, String apellido, String nroDocumento) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.nroDocumento = nroDocumento;
  }

  public long getId() {
    return id;
  }

  public String getNombre() {
    return this.nombre;
  }

  public String getApellido() {
    return this.apellido;
  }

  public String getNroDocumento() {
    return nroDocumento;
  }
  
  public double calcularHCTotal(Periodicidad periodicidad) {
    return this.consumos.stream().filter(c->c.getPeriodicidad()==periodicidad).mapToDouble(consumo -> consumo.calcularHC().enKilogramosCO2()).sum();
  }

  public void agregarConsumo(Consumo consumo) {
    consumos.add(consumo);
  }
}
