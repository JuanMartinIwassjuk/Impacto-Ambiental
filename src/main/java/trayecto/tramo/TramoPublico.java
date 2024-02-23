package trayecto.tramo;

import javax.persistence.*;

import medicionemisiones.HuellaDeCarbono;
import medicionemisiones.TipoConsumo;
import trayecto.Ubicacion;
import trayecto.mediodetransporte.transportepublico.Estacion;
import trayecto.mediodetransporte.transportepublico.TransportePublico;


@Entity
@Table(name = "TramoPublico")
public class TramoPublico extends Tramo {
  @OneToOne
  private final TransportePublico medioDeTransporte;

  @OneToOne
  private final Estacion origen;

  @OneToOne
  private final Estacion destino;
 

  public TramoPublico(TransportePublico medioDeTransporte, Estacion origen, Estacion destino, TipoConsumo tipoConsumo) {
    super(tipoConsumo);
    this.medioDeTransporte = medioDeTransporte;
    this.origen = origen;
    this.destino = destino;
  }

  public TransportePublico getMedioDeTransporte() {
    return medioDeTransporte;
  }

  public Estacion getOrigen() {
    return origen;
  }

  public Estacion getDestino() {
    return destino;
  }

  public Ubicacion getUbicOrigen() {
    return origen.getUbicacion();
  }

  public Ubicacion getUbicDestino() {
    return destino.getUbicacion();
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

