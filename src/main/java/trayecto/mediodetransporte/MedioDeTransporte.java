package trayecto.mediodetransporte;

import javax.persistence.*;

import services.geodds.ServiceGeodds;
import services.geodds.entities.DistanciaAPI;
import trayecto.Distancia;
import trayecto.Ubicacion;
@Entity
@Table(name = "MedioDeTransporte")
@DiscriminatorColumn(name = "discriminador_tipo_medio_transporte", discriminatorType = DiscriminatorType.INTEGER, length = 1)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class MedioDeTransporte {
  @Id
  @GeneratedValue
  private long id;

  @Transient
  private final ServiceGeodds serviceGeodds = new ServiceGeodds();

  private double litrosCombustiblePorKm;

  @Enumerated(EnumType.STRING)
  protected TipoCombustible tipoCombustible;

  public MedioDeTransporte(double litrosCombustiblePorKm, TipoCombustible tipoCombustible){
    this.litrosCombustiblePorKm = litrosCombustiblePorKm;
    this.tipoCombustible = tipoCombustible;
  }

  public Distancia distancia(Ubicacion origen, Ubicacion destino) {
    DistanciaAPI distanciaObt = serviceGeodds.distancia(origen, destino);
    return new Distancia(distanciaObt.getValor(), distanciaObt.getUnidad());
  }

  public double getLitrosCombustiblePorKm() {
    return this.litrosCombustiblePorKm;
  }
}
