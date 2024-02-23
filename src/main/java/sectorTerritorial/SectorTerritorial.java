package sectorTerritorial;

import organizacion.Organizacion;

import java.util.List;

import medicionemisiones.Periodicidad;

public class SectorTerritorial {

  TipoSector tipoSector;
  List<Organizacion> organizaciones;

  public SectorTerritorial(TipoSector tipoSector, List<Organizacion> organizaciones) {
    this.tipoSector = tipoSector;
    this.organizaciones = organizaciones;
  }

  public TipoSector getTipoSector() {
    return tipoSector;
  }

  public List<Organizacion> getOrganizaciones() {
    return organizaciones;
  }

  public double calcularHCTotal(Periodicidad periodicidad) {
    return organizaciones
        .stream()
        .mapToDouble(organizacion -> organizacion.calcularHCTotal(periodicidad))
        .sum();
  }

  public void addOrganizacion(Organizacion organizacion) {
    organizaciones.add(organizacion);
  }
}
