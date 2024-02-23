package motherobject;

import java.util.Arrays;
import java.util.List;

import medicionemisiones.*;
import org.junit.jupiter.api.BeforeEach;
import repositorios.RepositorioTipoConsumo;

public class RepositorioTipoConsumoMO {

    public static RepositorioTipoConsumo instance() {
        RepositorioTipoConsumo repo = RepositorioTipoConsumo.getRepositorioTipoConsumo();
        repo.setTiposConsumo(tiposConsumos());

        return repo;
    }

  public static List<TipoConsumo> tiposConsumos() {
    return Arrays.asList(
        new TipoConsumo(
            FuenteEnergetica.NAFTA,
            new FactorEmision(2.37, UnidadMedida.LITROS, EquivalenciaCO2.KILOGRAMO_CO2_EQ)
        ),
        new TipoConsumo(
            FuenteEnergetica.GAS,
            new FactorEmision(1.95, UnidadMedida.METROS_CUBICOS, EquivalenciaCO2.KILOGRAMO_CO2_EQ)
        ),
        new TipoConsumo(
            FuenteEnergetica.GASOIL,
            new FactorEmision(2.77, UnidadMedida.LITROS, EquivalenciaCO2.KILOGRAMO_CO2_EQ)
        ),
        new TipoConsumo(
            FuenteEnergetica.CARBON,
            new FactorEmision(3.78, UnidadMedida.KILOGRAMO, EquivalenciaCO2.KILOGRAMO_CO2_EQ)
        ),
        new TipoConsumo(
            FuenteEnergetica.GASOIL_CONSUMIDO,
            new FactorEmision(3.27, UnidadMedida.LITROS, EquivalenciaCO2.KILOGRAMO_CO2_EQ)
        ),
        new TipoConsumo(
            FuenteEnergetica.NAFTA_CONSUMIDA,
            new FactorEmision(3.11, UnidadMedida.LITROS, EquivalenciaCO2.KILOGRAMO_CO2_EQ)
        ),
        new TipoConsumo(
            FuenteEnergetica.ELECTRICIDAD,
            new FactorEmision(0.486, UnidadMedida.KILOWATT_HORA, EquivalenciaCO2.KILOGRAMO_CO2_EQ)
        ),
        new TipoConsumo(
            FuenteEnergetica.TRANSPORTE,
            new FactorEmision(0.63, UnidadMedida.KILOMETRO, EquivalenciaCO2.KILOGRAMO_CO2_EQ)
        ),
        new TipoConsumo(
            FuenteEnergetica.DISTANCIA,
            new FactorEmision(0.78, UnidadMedida.KILOMETRO, EquivalenciaCO2.KILOGRAMO_CO2_EQ)
        )
    );
  }
}
