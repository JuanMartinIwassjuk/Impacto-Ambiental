package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import medicionemisiones.Consumo;
import medicionemisiones.FactorEmision;
import medicionemisiones.FuenteEnergetica;
import medicionemisiones.Periodicidad;
import medicionemisiones.TipoConsumo;
import medicionemisiones.UnidadMedida;
import repositorios.RepositorioFactorEmision;
import repositorios.RepositorioOrganizacion;

public class PersistidorConsumoArchivo implements WithGlobalEntityManager, TransactionalOps{

	public void persistirArchivo(long idOrg, File archivoCsv) throws FileNotFoundException {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(archivoCsv);
		withTransaction(() -> {
		
			
			sc.hasNextLine();
			
			FuenteEnergetica fe = FuenteEnergetica.valueOf(sc.next());
			
			sc.hasNextLine();
			
			FactorEmision factorEmision = RepositorioFactorEmision.getInstance().buscarPorNombre(sc.next());
			
			TipoConsumo tipoconsumo = new TipoConsumo(fe, factorEmision);

			entityManager().persist(tipoconsumo);
			
			sc.hasNextLine();
			Periodicidad perioricidad = Periodicidad.valueOf(sc.next());
			
			sc.hasNextLine();
			double valorConsumo = Integer.parseInt(sc.next());
			
			sc.hasNextLine();
			Integer mes = Integer.parseInt(sc.next());
			
			sc.hasNextLine();
			Integer anio = Integer.parseInt(sc.next());
			
			Consumo consumo = new Consumo(tipoconsumo, perioricidad,
					valorConsumo, mes, anio);
			
			entityManager().persist(consumo);

			RepositorioOrganizacion.getInstance().buscarOrganizacionPorID(idOrg).addConsumo(consumo);

		
					
			});
	
	}

}
