package repositorios;

import organizacion.Area;
import organizacion.Miembro;
import organizacion.Organizacion;
import organizacion.Vinculacion;
import utils.PersistidorConsumoArchivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import medicionemisiones.Consumo;
import medicionemisiones.FactorEmision;
import medicionemisiones.FuenteEnergetica;
import medicionemisiones.Periodicidad;
import medicionemisiones.TipoConsumo;

public class RepositorioOrganizacion implements WithGlobalEntityManager, TransactionalOps {

	private static RepositorioOrganizacion instance;

	public static RepositorioOrganizacion getInstance() {
		if (instance == null)
			instance = new RepositorioOrganizacion();

		return instance;
	}

	public void agregar(Organizacion organizacion) {
		entityManager().persist(organizacion);
	}

	public List<Organizacion> listar() {
		return entityManager().createQuery("from Organizacion", Organizacion.class).getResultList();
	}

	public List<Organizacion> listarDisponiblesParaUsuario(String documento) {
		return this.listar().stream().filter(org -> !org.tieneMiembro(documento)).collect(Collectors.toList());
	}

	public Boolean anyMatchUser(String documento){
		return this.listar().stream().anyMatch(organizacion -> organizacion.tieneMiembro(documento));
	}

	public Organizacion buscarOrganizacionPorID(long id) {
		return entityManager().find(Organizacion.class, id);
	}

	public long ObtenerIdPorNombre(String nombre) {
		return entityManager().createQuery("from Organizacion c where c.razonSocial like :nombre", Organizacion.class)
				.setParameter("nombre", "%" + nombre + "%") //
				.getResultList().get(0).getId();
	}

	public List<Area> getAreas(long id) {
		return this.buscarOrganizacionPorID(id).getAreas();
	}

	public List<Vinculacion> getVinculaciones(long id) {
		return this.buscarOrganizacionPorID(id).getVinculaciones();
	}

	public List<Organizacion> getOrganizaciones() {
		return entityManager().createQuery("from Organizacion", Organizacion.class).getResultList();
	}

	public void persistirConsumoEnOrg(long idOrg, String fuenteEnergeticaSesion, String unidMedida,
			String equivalenciaCO2, String perioricidad, String valorConsumo, String mes, String anio) {

		withTransaction(() -> {
	
			FuenteEnergetica fuenteEnergetica = FuenteEnergetica.valueOf(fuenteEnergeticaSesion);

			FactorEmision factorEmision = RepositorioFactorEmision.getInstance().buscarPorNombre(unidMedida);
			TipoConsumo tipoconsumo = new TipoConsumo(fuenteEnergetica, factorEmision);

			entityManager().persist(tipoconsumo);

			Consumo consumo = new Consumo(tipoconsumo, Periodicidad.valueOf(perioricidad),
					Integer.parseInt(valorConsumo), Integer.parseInt(mes), Integer.parseInt(anio));

			entityManager().persist(consumo);

			RepositorioOrganizacion.getInstance().buscarOrganizacionPorID(idOrg).addConsumo(consumo);

		});

	}

	public List<Organizacion> obtenerOrganizacionesPorClasificacion(String clasificacion) {
			List<Organizacion> org = entityManager()
					.createQuery("select t from Organizacion t where t.tipo like \'" + clasificacion + "\' ",
							Organizacion.class)
					.getResultList();
			return org;
	}

	public void instanciarHCenOrganizaciones(List<Organizacion> orgs, Periodicidad periodicidad) {
		orgs.stream().forEach(o -> o.calcularHCTotal(periodicidad));
	}

	public void persistirMiembroEnOrg(long idOrg, Integer documento) {

		Organizacion organizacion = RepositorioOrganizacion.getInstance().buscarOrganizacionPorID(idOrg);
	
		System.out.println(organizacion.getRazonSocial());
		Vinculacion vinculacion = organizacion.econtrarVinculacionPorDocumento(documento);
	
		Miembro miembro = new Miembro(vinculacion.getNombre(), vinculacion.getApellido(),
				vinculacion.getNroDocumento());
	
		withTransaction(() -> {
		
			organizacion.eliminarVinculacion(vinculacion);
			entityManager().persist(miembro);
			organizacion.agregarMiembro(miembro);
		
		});
	}

	public void rechazarVinculacionDeOrg(long idOrg, Integer documento) {

		Organizacion organizacion = RepositorioOrganizacion.getInstance().buscarOrganizacionPorID(idOrg);
		Vinculacion vinculacion = organizacion.econtrarVinculacionPorDocumento(documento);

		withTransaction(() -> {
			organizacion.eliminarVinculacion(vinculacion);
			entityManager().remove(vinculacion);

		});

	}

	public void persistirVinculacion(long idArea, long idOrg, String nombre, String apellido, String documento,
			String motivo) {

		Organizacion org = RepositorioOrganizacion.getInstance().buscarOrganizacionPorID(idOrg);
		Area area = RepositorioArea.getInstance().buscarPorId(idArea);

		Vinculacion vinculacion = new Vinculacion(nombre, apellido, documento, motivo, area);
		withTransaction(() -> {
			entityManager().persist(vinculacion);
			org.agregarVinculacion(vinculacion);
		});
	}

	public List<Consumo> ObtenerConsumosDeOrg(long idOrg) {
		return this.buscarOrganizacionPorID(idOrg).getConsumosPropios();

	}

	public void persistirConsumoEnOrgPorArchivo(long idOrg, String path) throws FileNotFoundException {

		File archivo = new File(path);
		PersistidorConsumoArchivo persistidor = new PersistidorConsumoArchivo();
		persistidor.persistirArchivo(idOrg, archivo);
	}
}
