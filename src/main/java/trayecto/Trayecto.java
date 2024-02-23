package trayecto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import medicionemisiones.Consumo;
import medicionemisiones.Periodicidad;
import organizacion.Miembro;
import trayecto.tramo.Tramo;

@Entity
public class Trayecto {
  @Id
  @GeneratedValue
  private long id;

  private String descripcion;

  @OneToMany
  @JoinColumn(name = "trayecto_id")
  private List<Tramo> tramos = new ArrayList<>();

  @ManyToMany(cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
  })
  @JoinTable(name = "Trayecto_Miembro",
      joinColumns = @JoinColumn(name = "trayecto_id"),
      inverseJoinColumns = @JoinColumn(name = "miembro_id")
  )
  private List<Miembro> integrantes = new ArrayList<>();
  
  public Trayecto() {}

  public Trayecto(String descripcion, List<Miembro> miembros){
    this.descripcion = descripcion;
    this.integrantes = miembros;
  }
  
  public Trayecto(List<Tramo> tramos, List<Miembro> integrantes) {
    this.tramos = tramos;
    this.integrantes = integrantes;
  }

  public long getId() {
    return id;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Ubicacion getOrigen() {
    return this.tramos.get(0).getUbicOrigen();
  }

  public Ubicacion getDestino() {
    return this.tramos.get(this.tramos.size() - 1).getUbicDestino();
  }

  public void setTramos(List<Tramo> tramos) {
    this.tramos = tramos;
  }

  public void agregarTramo(Tramo tramo){
    tramos.add(tramo);
  }

  public List<Tramo> getTramos() {
    return this.tramos;
  }

  public boolean tieneIntegrante(Long id){
    return this.integrantes.stream().anyMatch(miembro -> miembro.getId() == id);
  }

  public void agregarIntegrante(Miembro integrante) {
    this.integrantes.add(integrante);
  }

  public List<Miembro> getIntegrantes() {
    return integrantes;
  }

  public Distancia distancia() {
    double distancia = tramos.stream().mapToDouble(Tramo::distancia).sum();
    return new Distancia(distancia, "KM");
  }

  public double calcularHCTotalTrayecto() {
    return tramos.stream().mapToDouble(tramo -> tramo.calcularHuellaCarbonoTramo().enKilogramosCO2()).sum();
  }

  public void agregarConsumoHCAMiembros() {
    tramos.forEach(tramo -> {
      this.integrantes.forEach(miembro -> miembro.agregarConsumo(new Consumo(tramo.getTipoConsumo(), Periodicidad.MENSUAL, tramo.calcularHuellaCarbonoTramo().enKilogramosCO2() / integrantes.size())));
    });
  }
}