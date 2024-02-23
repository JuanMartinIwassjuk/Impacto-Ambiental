package organizacion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import medicionemisiones.Periodicidad;

@Entity
public class Area {
	
  @Id
  @GeneratedValue
  private long id;

  private String nombre;

  @ManyToMany
  private List<Miembro> miembros;

  public Area() {
	this.nombre = "";
	this.miembros = new ArrayList<>();
  }
  
  public Area(String nombre, List<Miembro> miembros) {
    this.nombre = nombre;
    this.miembros = miembros;
  }

  public List<Miembro> getMiembros() {
    return miembros;
  }

  public void agregarMiembro(Miembro miembro) {
    this.miembros.add(miembro);
  }

  public String getNombre() {
    return nombre;
  }

  public Boolean tieneMiembro(String documento){
    return this.miembros.stream().anyMatch(miembro -> miembro.getNroDocumento().equals(documento));
  }

  public double valorHuellaCarbono(Periodicidad periodicidad) {
    return miembros
        .stream()
        .mapToDouble(m->m.calcularHCTotal(periodicidad))
        .sum();
  }

  public double indicadorHcPorMiembro(Periodicidad periodicidad) {
	  if(miembros.size()==0) {
		  return 0;
	  } else 
      return valorHuellaCarbono(periodicidad) / miembros.size();
  }
}