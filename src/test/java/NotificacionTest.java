import motherobject.ContactoMO;
import org.junit.jupiter.api.Test;
import organizacion.Clasificacion;
import organizacion.Organizacion;
import organizacion.TipoOrganizacion;
import recomendaciones.ListaDeEnvios;
import recomendaciones.MailClient;
import recomendaciones.WhatsappClient;
import trayecto.Ubicacion;

import javax.mail.MessagingException;
import javax.mail.Transport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NotificacionTest {

	Organizacion macowins = new Organizacion("macowins", TipoOrganizacion.EMPRESA,
			new Ubicacion("Lanus", "25 de Mayo", "3480"),
			Clasificacion.EMPRESA_SECTOR_SECUNDARIO);

	ContactoMO contactoMO = new ContactoMO();


	@Test
	public void notificarMailTest() throws MessagingException {
		Transport transportMock = mock(Transport.class);
		MailClient notificacarCorreo = new MailClient(session -> transportMock);

		notificacarCorreo.notificar("www.google.com", "correo@outlook.com");

		verify(transportMock, times(1)).connect(any(),any());
		verify(transportMock, times(1)).sendMessage(any(),any());
		verify(transportMock,times(1)).close();
	}

	@Test
	public void notificarMailTestNotifiacion() throws MessagingException {
		MailClient notificacarCorreo = mock(MailClient.class);

		notificacarCorreo.notificar("www.google.com", "correo@outlook.com");

		verify(notificacarCorreo, times(1)).notificar(any(),any());
	}

	@Test
	public void notificarContactosMailWhatsappTest() throws MessagingException {

		macowins.setContacto(contactoMO.contacto1());


		ListaDeEnvios listaDeEnvios = new ListaDeEnvios();



		MailClient notificacarCorreo = mock(MailClient.class);
		WhatsappClient whatsappClient = mock(WhatsappClient.class);

		listaDeEnvios.agregarInteresadosAlEvento(notificacarCorreo);
		listaDeEnvios.agregarInteresadosAlEvento(whatsappClient);

		listaDeEnvios.enviarNotificacionA("www.google.com",macowins);

		verify(notificacarCorreo, times(1)).notificarTiempoCumplido(any(),any());
		verify(whatsappClient, times(1)).notificarTiempoCumplido(any(),any());
	}

	@Test
	public void notificarWhatsappTest(){

		WhatsappClient whatsappClient = mock(WhatsappClient.class);
		whatsappClient.envio("www.google.com", "+5491111111111");

		verify(whatsappClient, times(1)).envio(any(),any());
	}




}