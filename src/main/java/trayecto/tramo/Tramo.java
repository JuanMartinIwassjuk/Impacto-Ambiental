package trayecto.tramo;

import javax.persistence.*;

import medicionemisiones.HuellaDeCarbono;
import medicionemisiones.TipoConsumo;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import trayecto.Ubicacion;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Tramo implements WithGlobalEntityManager {
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private Long id;

  @OneToOne
  protected TipoConsumo tipoConsumo;

  public Tramo(TipoConsumo tipoConsumo) {
    this.tipoConsumo = tipoConsumo;
  }

  abstract public double distancia();
  abstract public Ubicacion getUbicOrigen();
  abstract public Ubicacion getUbicDestino();
  abstract public HuellaDeCarbono calcularHuellaCarbonoTramo();

  public TipoConsumo getTipoConsumo() {
    return tipoConsumo;
  }

  public void setTipoConsumo(TipoConsumo tipoConsumo) {
    this.tipoConsumo = tipoConsumo;
  }
}
