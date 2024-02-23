package trayecto.tramo;

import javax.persistence.*;

import medicionemisiones.HuellaDeCarbono;
import medicionemisiones.TipoConsumo;
import trayecto.Ubicacion;
import trayecto.mediodetransporte.MedioDeTransporte;

@Entity
@Table(name = "TramoPrivado")
public class TramoPrivado extends Tramo {
  @OneToOne
  private MedioDeTransporte medioDeTransporte;

  @Embedded
  @AttributeOverrides({
          @AttributeOverride( name = "localidad", column = @Column(name = "origen_localidad")),
          @AttributeOverride( name = "calle", column = @Column(name = "origen_calle")),
          @AttributeOverride( name = "altura", column = @Column(name = "origen_altura"))
  })
  private Ubicacion origen;

  @Embedded
  @AttributeOverrides({
          @AttributeOverride( name = "localidad", column = @Column(name = "destino_localidad")),
          @AttributeOverride( name = "calle", column = @Column(name = "destino_calle")),
          @AttributeOverride( name = "altura", column = @Column(name = "destino_altura"))
  })
  private Ubicacion destino;

  public TramoPrivado(MedioDeTransporte medioDeTransporte, Ubicacion origen, Ubicacion destino, TipoConsumo tipoConsumo) {
    super(tipoConsumo);
    this.medioDeTransporte = medioDeTransporte;
    this.origen = origen;
    this.destino = destino;
  }

  public MedioDeTransporte getMedioDeTransporte() {
    return medioDeTransporte;
  }

  public Ubicacion getUbicOrigen() {
    return origen;
  }

  public Ubicacion getUbicDestino() {
    return destino;
  }

  public double distancia() {
    return medioDeTransporte.distancia(origen, destino).getValor();
  }

  public HuellaDeCarbono calcularHuellaCarbonoTramo() {
    double litrosConsumidos = medioDeTransporte.getLitrosCombustiblePorKm() * this.distancia();
    return new HuellaDeCarbono(
        litrosConsumidos,
        this.tipoConsumo
    );
  }
}
