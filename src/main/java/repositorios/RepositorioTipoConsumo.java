package repositorios;

import java.util.ArrayList;
import java.util.List;

import medicionemisiones.Periodicidad;
import medicionemisiones.TipoConsumo;
import organizacion.Organizacion;
import spark.Request;
import utils.Session;

public class RepositorioTipoConsumo {
  private List<TipoConsumo> tiposConsumo = new ArrayList<>();
  private static RepositorioTipoConsumo repositorioTipoConsumo;

  public static RepositorioTipoConsumo getRepositorioTipoConsumo() {
    if (repositorioTipoConsumo == null) {
      repositorioTipoConsumo = new RepositorioTipoConsumo();
    }
    return repositorioTipoConsumo;
  }

  public List<TipoConsumo> getTiposConsumo() {
    return this.tiposConsumo;
  }

  public void setTiposConsumo(List<TipoConsumo> tiposConsumo) {
    this.tiposConsumo = tiposConsumo;
  }

  public TipoConsumo buscarTipoDeConsumoPorNombre(String nombre) {
    return this.tiposConsumo.stream().filter(tipoConsumo -> tipoConsumo.getFuenteEnergetica().getDenominacion().equalsIgnoreCase(nombre)).findFirst().get();
  }
  
  public double calcularHCsegunUser(Request req,Periodicidad periodicidad) {
	    if (Session.getAttribute(req, "user_rol").equals("organizacion")) {
	      Organizacion org = RepositorioOrganizacion.getInstance().buscarOrganizacionPorID(req.session().attribute("idorg"));
	      return org.calcularHCTotal(periodicidad);
	    } else {
	      return RepositorioMiembro.getInstance().buscarMiembroPorID(Long.parseLong(Session.getAttribute(req, "user_id"))).calcularHCTotal(periodicidad);
	    }
	  }
  
}
