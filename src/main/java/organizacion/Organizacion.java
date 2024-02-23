package organizacion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import medicionemisiones.Consumo;
import medicionemisiones.Periodicidad;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import recomendaciones.Contacto;
import trayecto.Ubicacion;

@Entity
public class Organizacion {
	@Id
	@GeneratedValue
	private long id;

	private String razonSocial;

	@Enumerated(EnumType.STRING)
	private TipoOrganizacion tipo;

	@Embedded
	private Ubicacion ubicacion;

	@Enumerated(EnumType.STRING)
	private Clasificacion clasificacion;

	@OneToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "organizacion_id")
	private List<Area> areas = new ArrayList<>();

	@OneToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "organizacion_id")
	private List<Consumo> consumosPropios = new ArrayList<>();

	@OneToOne
	public Contacto contacto;

	@OneToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "organizacion_id")
	private List<Vinculacion> vinculaciones = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<Recomendacion> recomendaciones = new ArrayList<>();

	public Organizacion() {
		this.razonSocial = "";
		this.tipo = null;
		this.ubicacion = null;
		this.clasificacion = null;
	}

	public Organizacion(String razonSocial, TipoOrganizacion tipo, Ubicacion ubicacion, Clasificacion clasificacion) {
		this.razonSocial = razonSocial;
		this.tipo = tipo;
		this.ubicacion = ubicacion;
		this.clasificacion = clasificacion;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public long getId() {
		return id;
	}

	public TipoOrganizacion getTipo() {
		return tipo;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public Clasificacion getClasificacion() {
		return clasificacion;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public List<Vinculacion> getVinculaciones() {
		return vinculaciones;
	}

	public void agregarArea(Area area) {
		this.areas.add(area);
	}

	public Contacto getContacto() {
		return contacto;
	}

	public List<Consumo> getConsumosPropios() {
		return consumosPropios;
	}

	public Boolean tieneMiembro(String documento){
		return this.areas.stream().anyMatch(area -> area.tieneMiembro(documento));
	}

	public double calcularHCTotalConsumosPropios(Periodicidad periodicidad) {
		return consumosPropios.stream().filter(c -> c.getPeriodicidad()==periodicidad)
				.mapToDouble(consumo -> 
				consumo.calcularHC().enKilogramosCO2()).sum();
	}
	
	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	public void agregarMiembro(Miembro miembro) {
		this.getAreas().get(0).agregarMiembro(miembro);
	}

	public double calcularHCTotalAreas(Periodicidad periodicidad) {
		return getAreas().stream().mapToDouble(a -> a.valorHuellaCarbono(periodicidad)).sum();
	}

	public void addConsumo(Consumo consumo) {
		this.consumosPropios.add(consumo);
	}

	public void addConsumos(List<Consumo> consumos) {
		consumos.forEach(this::addConsumo);
	}

	public double calcularHCTotal(Periodicidad periodicidad) {
		if (periodicidad == Periodicidad.ANUAL) {
			return calcularHCTotalAreas(periodicidad) + calcularHCTotalConsumosPropios(periodicidad);
		} else {
			return calcularHCTotalAreas(periodicidad) + calcularHCTotalConsumosPropios(periodicidad);
		}
	}

	public void agregarVinculacion(Vinculacion vinculacion) {
		this.vinculaciones.add(vinculacion);
	}

	public void eliminarVinculacion(Vinculacion vinculacion) {
		this.vinculaciones.remove(vinculacion);
	}

	public Vinculacion econtrarVinculacionPorDocumento(Integer documento) {
		return this.vinculaciones.stream().filter(u -> u.getNroDocumento().equals(documento.toString())).findFirst().get();
	}

}
