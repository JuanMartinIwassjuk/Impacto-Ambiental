package trayecto.mediodetransporte.transportepublico;

import trayecto.Distancia;
import trayecto.mediodetransporte.TipoCombustible;

import javax.persistence.*;

@Entity
public class TransportePublico {

  @Id
  @GeneratedValue
  private Long id;

  @Enumerated(EnumType.STRING)
  private TipoTransportePublico tipoTransportePublico;

  @OneToOne
  private Linea linea;

  @Enumerated(EnumType.STRING)
  private TipoCombustible tipoCombustible;

  private double litrosCombustiblePorKm;

  public TransportePublico(TipoTransportePublico tipoTransportePublico,
                           Linea linea,
                           TipoCombustible tipoCombustible,
                           double litrosCombustiblePorKm) {
    this.tipoTransportePublico = tipoTransportePublico;
    this.linea = linea;
    this.tipoCombustible = tipoCombustible;
    this.litrosCombustiblePorKm = litrosCombustiblePorKm;
  }

  public TipoTransportePublico getTipoTransportePublico() {
    return tipoTransportePublico;
  }

  public String getTipo() {
    return this.tipoTransportePublico.toString();
  }

  public Linea getLinea() {
    return linea;
  }

  public double getLitrosCombustiblePorKm() {
    return litrosCombustiblePorKm;
  }

  public Distancia distancia(Estacion origen, Estacion destino) {
    return linea.distancia(origen, destino);
  }
}