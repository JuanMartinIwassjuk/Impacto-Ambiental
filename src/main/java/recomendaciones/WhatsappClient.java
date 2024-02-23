package recomendaciones;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class WhatsappClient implements InteresadosEnGuia{

  public static final String ACCOUNT_SID = "AC87399d9902a606a75227fc15bc4c9395";
  public static final String AUTH_TOKEN = "3ea1c0eb0f0ae919c0c1169b35d907b6";

  @Override
  public void notificarTiempoCumplido(String link, Contacto contacto) {
    enviarWhatsapp(link, contacto);
  }

  public void enviarWhatsapp(String link, Contacto contacto) {
    envio(link, contacto.getNumeroTelefono());
  }

  public void envio(String Link, String numero) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message message = Message.creator(
            new com.twilio.type.PhoneNumber("whatsapp:" + numero ),
            new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
            "Guia recomendaciones: "+ Link)
        .create();

    System.out.println(message.getSid());
  }
}
