package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import repositorios.RepositorioTipoConsumo;
import exceptions.FormatoCSVConsumoException;
import medicionemisiones.Consumo;
import medicionemisiones.Periodicidad;
import medicionemisiones.TipoConsumo;

public class LectorCsvConsumos {
  private final String SEPARADOR_LINEA = "\\n";
  private String separadorCampo;
  private String filename;
  private List<Consumo> consumos = new ArrayList<>();

  public LectorCsvConsumos(String separadorCampo, String filename) {
    this.separadorCampo = separadorCampo;
    this.filename = filename;
  }

  public List<Consumo> obtenerConsumos() {
    this.procesarCsv();
    return this.consumos;
  }

  private void procesarCsv() {
    Arrays.stream(ReadTextAsString
            .readFileAsString(filename)
            .split(SEPARADOR_LINEA))
        .forEach(this::obtenerConsumoDe);
  }

  private void obtenerConsumoDe(String linea) {
    if (linea.split(this.separadorCampo).length != 4) {
      throw new FormatoCSVConsumoException("Se ingresaron "
          + linea.split(this.separadorCampo).length
          + "campos para un consumo cuando se esperan 4");
    }
    consumos.add(new Consumo(
        this.obtenerTipoConsumo(linea.split(this.separadorCampo)[0].trim()),
        this.obtenerPeriodicidad(linea.split(this.separadorCampo)[2].trim()),
        this.obtenerValorConsumo(linea.split(this.separadorCampo)[1].trim()),
        this.obtenerMes(linea.split(this.separadorCampo)[3].trim()),
        this.obtenerAnio(linea.split(this.separadorCampo)[3].trim())
    ));
  }

  private Integer obtenerMes(String periodo) {
    if (periodo.length() == 6) {
      return Integer.valueOf(periodo.substring(0, 2));
    } else {
      return null;
    }
  }

  private Integer obtenerAnio(String periodo) {
    return Integer.valueOf(periodo.substring(periodo.length() - 4));
  }

  private Periodicidad obtenerPeriodicidad(String periodicidad) {
    return Arrays
        .stream(Periodicidad.values())
        .filter(p -> p.getCodigo()
            .equals(periodicidad))
        .collect(Collectors.toList()).get(0);
  }

  private double obtenerValorConsumo(String valor) {
    return Double.valueOf(Double.parseDouble(valor));
  }

  private TipoConsumo obtenerTipoConsumo(String fuenteEnergetica) {
    return RepositorioTipoConsumo.getRepositorioTipoConsumo().buscarTipoDeConsumoPorNombre(fuenteEnergetica);
  }
}
