package motherobject;

import medicionemisiones.Consumo;
import medicionemisiones.TipoConsumo;
import organizacion.Miembro;

import java.util.ArrayList;
import java.util.List;

import organizacion.Area;

public class AreaMO {
	ConsumoMO consumoMO = new ConsumoMO();
	public Area area1() {
		List<Miembro> miembros1 = new ArrayList<>();
		Miembro lucas = new Miembro("Lucas", "Díaz", "28610511");
		lucas.agregarConsumo(consumoMO.consumo1());
	
		miembros1.add(lucas);
	
	    return new Area("RRHH",miembros1);
	  }

	
	public Area area2() {
		List<Miembro> miembros1 = new ArrayList<>();
		Miembro lucas = new Miembro("Lucas", "Díaz", "28610511");
		lucas.agregarConsumo(consumoMO.consumo2());
		Miembro martin = new Miembro("Martin", "Noches", "28610511");
		martin.agregarConsumo(consumoMO.consumo3());
		Miembro roberto = new Miembro("Roberto", "Buendia", "28610511");
		roberto.agregarConsumo(consumoMO.consumo4());
		miembros1.add(lucas);
		miembros1.add(martin);
		miembros1.add(roberto);
	    return new Area("Marketing",miembros1);
	  }
	
	
	public Area area3() {
		List<Miembro> miembros1 = new ArrayList<>();
	
	    return new Area("RedesSociales",miembros1);
	  }


}
