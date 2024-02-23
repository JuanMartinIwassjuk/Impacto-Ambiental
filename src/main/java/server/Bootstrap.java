package server;

import admin.Usuario;
import medicionemisiones.Consumo;
import medicionemisiones.EquivalenciaCO2;
import medicionemisiones.FactorEmision;
import medicionemisiones.FuenteEnergetica;
import medicionemisiones.Periodicidad;
import medicionemisiones.TipoConsumo;
import medicionemisiones.UnidadMedida;
import organizacion.Area;
import organizacion.Clasificacion;
import organizacion.Miembro;
import organizacion.Organizacion;
import organizacion.TipoOrganizacion;
import organizacion.Vinculacion;
import recomendaciones.Contacto;
import repositorios.RepositorioArea;
import repositorios.RepositorioMiembro;
import repositorios.RepositorioUsuarios;
import trayecto.Ubicacion;

import java.util.ArrayList;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
	public static void main(String[] args) {
		new Bootstrap().run();
	}

	public void run() {
		withTransaction(() -> {
			FactorEmision km = new FactorEmision(2, UnidadMedida.KILOMETRO, EquivalenciaCO2.KILOGRAMO_CO2_EQ);
			entityManager().persist(km);
			FactorEmision kg = new FactorEmision(3, UnidadMedida.KILOGRAMO, EquivalenciaCO2.KILOGRAMO_CO2_EQ);
			entityManager().persist(kg);
			FactorEmision mc = new FactorEmision(1, UnidadMedida.METROS_CUBICOS, EquivalenciaCO2.KILOGRAMO_CO2_EQ);
			entityManager().persist(mc);
			FactorEmision lt = new FactorEmision(4, UnidadMedida.LITROS, EquivalenciaCO2.KILOGRAMO_CO2_EQ);
			entityManager().persist(lt);
			FactorEmision kh = new FactorEmision(5, UnidadMedida.KILOWATT_HORA, EquivalenciaCO2.KILOGRAMO_CO2_EQ);
			entityManager().persist(kh);

			RepositorioUsuarios.getInstance().agregar(new Usuario("jorge", "delaselva123", "28610511", "miembro"));
			RepositorioUsuarios.getInstance().agregar(new Usuario("Macowins", "Messi1234", "63572215", "organizacion"));
			RepositorioUsuarios.getInstance().agregar(new Usuario("Facebook", "Mark1234", "75693169", "organizacion"));
			RepositorioUsuarios.getInstance().agregar(new Usuario("Mercadolibre", "Jhon1234", "68932478", "organizacion"));
			RepositorioUsuarios.getInstance()
					.agregar(new Usuario("QueMePongo", "Messi1234", "73572215", "organizacion"));

			Miembro jorge = new Miembro("Jorge", "Díaz", "28610511");
			RepositorioMiembro.getInstance().agregar(jorge);

			List<Miembro> miembros = new ArrayList<>();
			miembros.add(jorge);
			Area area = new Area("Scouting", miembros);
			RepositorioArea.getInstance().agregar(area);

			Organizacion macowins = new Organizacion("Macowins", TipoOrganizacion.EMPRESA,
					new Ubicacion("Lanus", "25 de Mayo", "3480"), Clasificacion.EMPRESA_SECTOR_SECUNDARIO);
			entityManager().persist(macowins);

			Miembro locelso = new Miembro("Lo", "Celso", "32322323");
			RepositorioMiembro.getInstance().agregar(locelso);

			List<Miembro> miembrosmaco = new ArrayList<>();
			miembrosmaco.add(locelso);

			Area area12 = new Area("RRHH", miembrosmaco);
			RepositorioArea.getInstance().agregar(area12);

			macowins.agregarArea(area12);
			macowins.agregarArea(area);

			Contacto contacto12 = new Contacto("12342636273", "jorgelin@gmail.com");
			entityManager().persist(contacto12);
			macowins.setContacto(contacto12);

			Vinculacion vinculacion111 = new Vinculacion("julian", "rodriguez", "241421", "me caen bien", area12);
			entityManager().persist(vinculacion111);
			macowins.agregarVinculacion(vinculacion111);

			TipoConsumo gasoil = new TipoConsumo(FuenteEnergetica.GASOIL, kh);
			entityManager().persist(gasoil);

			Consumo consumoGasoil = new Consumo(gasoil,Periodicidad.ANUAL,1000,10,2022);
			entityManager().persist(consumoGasoil);

			macowins.addConsumo(consumoGasoil);

      //----------------------------------------------------------------------------------------------------------------

			Organizacion qmp = new Organizacion("QueMePongo", TipoOrganizacion.EMPRESA,
					new Ubicacion("Lanus", "25 de Mayo", "3480"), Clasificacion.EMPRESA_SECTOR_SECUNDARIO);
			entityManager().persist(qmp);

			Miembro lucas2 = new Miembro("Rodrigo", "Díaz", "378453579");
			entityManager().persist(lucas2);

			List<Miembro> miembros1 = new ArrayList<>();
			miembros1.add(lucas2);

			Area area1 = new Area("RRHH", miembros1);
			entityManager().persist(area1);

			qmp.agregarArea(area1);

			FactorEmision factorEmision1 = new FactorEmision(12, UnidadMedida.KILOGRAMO,
					EquivalenciaCO2.KILOGRAMO_CO2_EQ);
			entityManager().persist(factorEmision1);

			TipoConsumo tipoConsumo1 = new TipoConsumo(FuenteEnergetica.NAFTA, factorEmision1);
			entityManager().persist(tipoConsumo1);

			Consumo consumo1 = new Consumo(tipoConsumo1,Periodicidad.ANUAL,1000,03,2022);
			entityManager().persist(consumo1);

			qmp.addConsumo(consumo1);
			
			
			
			
			
			
			
			
			

			FactorEmision factorEmision4 = new FactorEmision(12, UnidadMedida.KILOGRAMO,
					EquivalenciaCO2.KILOGRAMO_CO2_EQ);
			entityManager().persist(factorEmision4);

			TipoConsumo tipoConsumo4 = new TipoConsumo(FuenteEnergetica.NAFTA, factorEmision4);
			entityManager().persist(tipoConsumo4);

			Consumo consumo4 = new Consumo(tipoConsumo4,Periodicidad.MENSUAL,230,05,2022);
			entityManager().persist(consumo4);

			qmp.addConsumo(consumo4);


			FactorEmision factorEmision5 = new FactorEmision(12, UnidadMedida.KILOGRAMO,
					EquivalenciaCO2.KILOGRAMO_CO2_EQ);
			entityManager().persist(factorEmision5);

			TipoConsumo tipoConsumo5 = new TipoConsumo(FuenteEnergetica.NAFTA, factorEmision5);
			entityManager().persist(tipoConsumo5);

			Consumo consumo5 = new Consumo(tipoConsumo5,Periodicidad.MENSUAL,666,02,2022);
			entityManager().persist(consumo5);

			qmp.addConsumo(consumo5);

			

			FactorEmision factorEmision6 = new FactorEmision(12, UnidadMedida.KILOWATT_HORA,
					EquivalenciaCO2.KILOGRAMO_CO2_EQ);
			entityManager().persist(factorEmision6);

			TipoConsumo tipoConsumo6 = new TipoConsumo(FuenteEnergetica.ELECTRICIDAD, factorEmision6);
			entityManager().persist(tipoConsumo6);

			Consumo consumo6 = new Consumo(tipoConsumo6,Periodicidad.MENSUAL,666,01,2022);
			entityManager().persist(consumo6);

			qmp.addConsumo(consumo6);

			
			
			
			
			

			Contacto contacto1 = new Contacto("1234263673", "jorge@gmail.com");
			entityManager().persist(contacto1);

			qmp.setContacto(contacto1);
			Vinculacion vinculacion11 = new Vinculacion("julian", "rodriguez", "23456765", "me caen bien", area1);
			entityManager().persist(vinculacion11);
			qmp.agregarVinculacion(vinculacion11);

			//----------------------------------------------------------------------------------------------------------------

			Organizacion utn = new Organizacion("UTN", TipoOrganizacion.INSTITUCION,
					new Ubicacion("Medrano", "25 de Mayo", "3480"), Clasificacion.UNIVERSIDAD);
			entityManager().persist(utn);

			Miembro franco = new Miembro("franco", "caputo", "287937812");
			RepositorioMiembro.getInstance().agregar(franco);

			List<Miembro> miembrosutn = new ArrayList<>();
			miembrosutn.add(franco);

			Area recepcion = new Area("Recepcion", miembrosutn);
			RepositorioArea.getInstance().agregar(recepcion);

			utn.agregarArea(recepcion);

			Contacto contactoUTN = new Contacto("116873579", "facebook@gmail.com");
			entityManager().persist(contactoUTN);
			utn.setContacto(contactoUTN);

			TipoConsumo nafta = new TipoConsumo(FuenteEnergetica.NAFTA, kh);
			entityManager().persist(nafta);

			Consumo consumoNafta = new Consumo(nafta,Periodicidad.ANUAL,1000,10,2022);
			entityManager().persist(consumoNafta);

			utn.addConsumo(consumoNafta);

			//----------------------------------------------------------------------------------------------------------------

			Organizacion facebook = new Organizacion("Facebook", TipoOrganizacion.EMPRESA,
					new Ubicacion("Lanus", "25 de Mayo", "3480"), Clasificacion.EMPRESA_SECTOR_SECUNDARIO);
			entityManager().persist(facebook);

			Miembro mark = new Miembro("mark", "sucker", "268733397");
			RepositorioMiembro.getInstance().agregar(mark);

			List<Miembro> miembrosface = new ArrayList<>();
			miembrosmaco.add(mark);

			Area contable = new Area("Contable", miembrosface);
			RepositorioArea.getInstance().agregar(contable);

			facebook.agregarArea(contable);

			Contacto contactoFace = new Contacto("116873579", "facebook@gmail.com");
			entityManager().persist(contactoFace);
			facebook.setContacto(contactoFace);

			TipoConsumo carbon = new TipoConsumo(FuenteEnergetica.CARBON, kh);
			entityManager().persist(carbon);

			Consumo consumoCarbon = new Consumo(carbon,Periodicidad.ANUAL,1000,10,2022);
			entityManager().persist(consumoCarbon);

			facebook.addConsumo(consumoCarbon);


			//----------------------------------------------------------------------------------------------------------------


			Organizacion mercadolibre = new Organizacion("MercadoLibre", TipoOrganizacion.EMPRESA,
					new Ubicacion("Balvanera", "25 de Mayo", "3480"), Clasificacion.EMPRESA_SECTOR_PRIMARIO);
			entityManager().persist(mercadolibre);

			Miembro jhon = new Miembro("jhon", "jil", "796769241");
			RepositorioMiembro.getInstance().agregar(jhon);

			List<Miembro> miembrosmerca = new ArrayList<>();
			miembrosmaco.add(jhon);

			Area rrpp = new Area("Relaciones personales", miembrosmerca);
			RepositorioArea.getInstance().agregar(rrpp);

			mercadolibre.agregarArea(rrpp);

			Contacto contactoMerca = new Contacto("1188939672", "mercadolibre@gmail.com");
			entityManager().persist(contactoMerca);
			mercadolibre.setContacto(contactoMerca);

			TipoConsumo gas = new TipoConsumo(FuenteEnergetica.GAS, mc);
			entityManager().persist(gas);

			Consumo consumoGas = new Consumo(gas,Periodicidad.ANUAL,1000,10,2022);
			entityManager().persist(consumoGas);

			mercadolibre.addConsumo(consumoGas);

		  // Org Caritas --------------
			  Organizacion caritas = new Organizacion("Caritas", TipoOrganizacion.ONG, new
			  Ubicacion("Lanus", "25 de Mayo", "3480"),
			  Clasificacion.EMPRESA_SECTOR_SECUNDARIO); entityManager().persist(caritas);
			  
			  Miembro lucas33 = new Miembro("Martin", "Lopetegui", "2323232323");
			 entityManager().persist(lucas33);
			  
			  List<Miembro> miembros11 = new ArrayList<>(); miembros1.add(lucas33); Area
			  area13 = new Area("RRHH", miembros11); entityManager().persist(area13);
			  
			  caritas.agregarArea(area13);
			  
			  FactorEmision factorEmision11 = new FactorEmision(12, UnidadMedida.KILOGRAMO,
			  EquivalenciaCO2.KILOGRAMO_CO2_EQ); entityManager().persist(factorEmision11);
			  
			  TipoConsumo tipoConsumo11 = new TipoConsumo(FuenteEnergetica.NAFTA,
			  factorEmision1); entityManager().persist(tipoConsumo11);
		
			  Consumo consumo11 = new Consumo(tipoConsumo11,Periodicidad.ANUAL, 2300,12,2022); 
			  entityManager().persist(consumo11); caritas.addConsumo(consumo11);
			  
			  Contacto contacto11 = new Contacto("1234263673", "jorge@gmail.com");
			  entityManager().persist(contacto11);
			  
			  caritas.setContacto(contacto11); Vinculacion vinculacion1111 = new
			  Vinculacion("julian", "rodriguez", "23456765", "me caen bien", area13);
			  Vinculacion vinculacion221 = new Vinculacion("sergio", "manchini", "23456765",
			  "me caen bien", area13); entityManager().persist(vinculacion1111);
			  entityManager().persist(vinculacion221);
			  caritas.agregarVinculacion(vinculacion1111);
			  caritas.agregarVinculacion(vinculacion221);
			  
			  // Org Gobierno ---------------
			
			  Organizacion gob = new
			  Organizacion("Centro de Trámites de las Exportaciones",
			  TipoOrganizacion.GUBERNAMENTAL, new Ubicacion("Lanus", "25 de Mayo", "3480"),
			  Clasificacion.EMPRESA_SECTOR_SECUNDARIO);
			  entityManager().persist(gob);
			  
			  Miembro lucas73 = new Miembro("Ricardo", "Lopez", "7565348");
				 entityManager().persist(lucas73);
				  
				  List<Miembro> miembros7 = new ArrayList<>(); miembros7.add(lucas73); Area
				  area23 = new Area("RRHH", miembros7); entityManager().persist(area23);
                gob.agregarArea(area23);
			  
                
        		FactorEmision factorEmision7 = new FactorEmision(12, UnidadMedida.KILOGRAMO,
    					EquivalenciaCO2.KILOGRAMO_CO2_EQ);
    			entityManager().persist(factorEmision7);

    			TipoConsumo tipoConsumo7 = new TipoConsumo(FuenteEnergetica.NAFTA, factorEmision7);
    			entityManager().persist(tipoConsumo7);

    			Consumo consumo7 = new Consumo(tipoConsumo7,Periodicidad.MENSUAL,200,06,2021);
    			entityManager().persist(consumo7);

    			gob.addConsumo(consumo7);
              
                
    			  Contacto contacto7 = new Contacto("1234263673", "jorge@gmail.com");
    			  entityManager().persist(contacto7);
    			  
			  
			  gob.setContacto(contacto7);
			  
			 Vinculacion vinculacion7 = new
					  Vinculacion("juliana", "sanchez", "234356765", "me caen bien", area23);
					 entityManager().persist(vinculacion7);
					 
					 gob.agregarVinculacion(vinculacion7);
					
			  
			 
			
			 

		});

	}

}
