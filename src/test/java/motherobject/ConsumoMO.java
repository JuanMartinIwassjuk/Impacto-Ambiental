package motherobject;

import medicionemisiones.Consumo;
import medicionemisiones.FuenteEnergetica;
import medicionemisiones.Periodicidad;
import medicionemisiones.TipoConsumo;
import repositorios.RepositorioTipoConsumo;

public class ConsumoMO {

  RepositorioTipoConsumo repo = RepositorioTipoConsumoMO.instance();

  TipoConsumo nafta = repo
      .getRepositorioTipoConsumo()
      .buscarTipoDeConsumoPorNombre(FuenteEnergetica.NAFTA.getDenominacion());

  public Consumo consumo1() {
    return new Consumo(nafta, Periodicidad.MENSUAL, 100, 5, 2022);
  }
  public Consumo consumo2() {
    return new Consumo(nafta, Periodicidad.MENSUAL, 250, 1, 2022);
  }
  public Consumo consumo3() {
    return new Consumo(nafta, Periodicidad.MENSUAL, 150, 8, 2022);
  }
  public Consumo consumo4() {
    return new Consumo(nafta, Periodicidad.MENSUAL, 500, 3, 2022);
  }
}
