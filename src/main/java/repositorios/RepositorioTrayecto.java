package repositorios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import io.jsonwebtoken.io.IOException;
import medicionemisiones.EquivalenciaCO2;
import medicionemisiones.FactorEmision;
import medicionemisiones.FuenteEnergetica;
import medicionemisiones.TipoConsumo;
import medicionemisiones.UnidadMedida;
import organizacion.Miembro;
import organizacion.Organizacion;
import spark.Request;
import spark.Response;
import trayecto.Trayecto;
import trayecto.Ubicacion;
import trayecto.mediodetransporte.MedioDeTransporte;
import trayecto.mediodetransporte.TipoCombustible;
import trayecto.mediodetransporte.TransporteContratado;
import trayecto.mediodetransporte.TransporteParticular;
import trayecto.mediodetransporte.tipostransporte.TipoTransporteContratado;
import trayecto.mediodetransporte.tipostransporte.TipoVehiculo;
import trayecto.tramo.TramoPrivado;
import utils.Session;

public class RepositorioTrayecto implements WithGlobalEntityManager, TransactionalOps {

  private static RepositorioTrayecto instance;

  public static RepositorioTrayecto getInstance() {
    if (instance == null)
      instance = new RepositorioTrayecto();

    return instance;
  }

  public List<Trayecto> listar() {
    return entityManager()
      .createQuery("from Trayecto", Trayecto.class)
      .getResultList();
  }

  public Trayecto buscarTrayectoporId(long id) {
    return entityManager().find(Trayecto.class, id);
  }

  public List<Trayecto> buscarTrayectosDeUsuario(long id) {
    return this.listar().stream().filter(trayecto -> trayecto.tieneIntegrante(id)).collect(Collectors.toList());
  }

  public void agregar(Trayecto trayecto) {
    entityManager().persist(trayecto);
  }

  public void crearTrayecto(Request req, Response res) {
    String descripcion = req.queryParams("descripcion");
    String documentos = req.queryParams("documentos");

    Miembro principal = RepositorioMiembro.getInstance().buscarMiembroPorDocumento(Session.getAttribute(req, "user_documento"));

    List<Miembro> miembros = new ArrayList<>();
    miembros.add(principal);

    withTransaction(() -> {
      Arrays.asList(documentos.split(","))
        .forEach(doc -> miembros.add(RepositorioMiembro.getInstance().buscarMiembroPorDocumento(doc)));

      Trayecto trayecto = new Trayecto(descripcion, miembros);

      RepositorioTrayecto.getInstance().agregar(trayecto);
    });

  }

  public void agregarTramo(Request req, Response res) {


    try {
      withTransaction(() -> {
        Ubicacion origen = new Ubicacion(req.queryParams("localidadorigen"), req.queryParams("calleorigen"), req.queryParams("alturaorigen"));
        Ubicacion destino = new Ubicacion(req.queryParams("localidaddestino"), req.queryParams("calledestino"), req.queryParams("alturadestino"));
        FactorEmision fe = new FactorEmision((double) Long.parseLong(req.queryParams("valorFe")), UnidadMedida.valueOf(req.queryParams("unidadMedida")), EquivalenciaCO2.valueOf(req.queryParams("equivalencia")));
        entityManager().persist(fe);

        TipoConsumo tc = new TipoConsumo(FuenteEnergetica.valueOf(req.queryParams("FuenteEnergetica")), fe);
        entityManager().persist(tc);
        TransporteContratado tp = new TransporteContratado(TipoTransporteContratado.valueOf(req.queryParams("tipoTransporteContratado")), Long.parseLong(req.queryParams("litrosCombustiblePorKm")), TipoCombustible.valueOf(req.queryParams("tipoCombustible")));
        entityManager().persist(tp);


        TramoPrivado tramoPrivado = new TramoPrivado(tp, origen, destino, tc);
				entityManager().persist(tramoPrivado);
        //Trayecto trayecto = this.buscarTrayectoporId(Long.parseLong(req.session().attribute("trayecto_id")));
				//trayecto.agregarTramo(tramoPrivado);

      });
    } catch (NoSuchElementException e) {

      withTransaction(() -> {
          Ubicacion origen = new Ubicacion(req.queryParams("localidadorigen"), req.queryParams("calleorigen"), req.queryParams("alturaorigen"));
          Ubicacion destino = new Ubicacion(req.queryParams("localidaddestino"), req.queryParams("calledestino"), req.queryParams("alturadestino"));
          FactorEmision fe = new FactorEmision((double) Long.parseLong(req.queryParams("valorFe")), UnidadMedida.valueOf(req.queryParams("unidadMedida")), EquivalenciaCO2.valueOf(req.queryParams("equivalencia")));
          entityManager().persist(fe);

          TipoConsumo tc = new TipoConsumo(FuenteEnergetica.valueOf(req.queryParams("FuenteEnergetica")), fe);
          entityManager().persist(tc);

          TransporteParticular tp = new TransporteParticular(TipoVehiculo.valueOf(req.queryParams("tipoVehiculo")), TipoCombustible.valueOf(req.queryParams("tipoCombustible")), Long.parseLong(req.queryParams("litrosCombustiblePorKm")));
          entityManager().persist(tp);


          TramoPrivado tramoPrivado = new TramoPrivado(tp, origen, destino, tc);
					entityManager().persist(tramoPrivado);
				//Trayecto trayecto = this.buscarTrayectoporId(Long.parseLong(req.session().attribute("trayecto_id")));
				//trayecto.agregarTramo(tramoPrivado);
        }


      );
    }


  }

}
