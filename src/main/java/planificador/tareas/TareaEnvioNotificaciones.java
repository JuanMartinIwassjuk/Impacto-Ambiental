package planificador.tareas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recomendaciones.ListaDeEnvios;
import recomendaciones.MailClient;
import recomendaciones.WhatsappClient;
import repositorios.RepositorioOrganizacion;

public class TareaEnvioNotificaciones{
  private final Logger log = LoggerFactory.getLogger(TareaEnvioNotificaciones.class);
  private final String LINK = "www.miimpactoambiental.com/recomendaciones";

  public void execute() {
    log.info("Enviando notificaciones...");
    ListaDeEnvios listaEventos = new ListaDeEnvios();
    listaEventos.agregarInteresadosAlEvento(new MailClient());
    listaEventos.agregarInteresadosAlEvento(new WhatsappClient());
    RepositorioOrganizacion.getInstance().listar()
        .forEach(organizacion -> listaEventos.enviarNotificacionA(LINK, organizacion));
    log.info("Notificaciones enviadas");
  }
}
