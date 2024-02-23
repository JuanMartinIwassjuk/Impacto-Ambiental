package recomendaciones;

import exceptions.NoSePudoEnviarEmail;
import exceptions.NotificacionCorreoException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;

public class MailClient implements InteresadosEnGuia{

  private String mailRemitente = "ddsnoche@gmail.com";

  private String passAppRemitente = "vpvjzvqcyiodcomt";
  private Session session = configurarConexionCorreo();

  //para test
  private Function <Session, Transport> funcion;

  public void notificarTiempoCumplido(String link, Contacto contacto) {
    enviarMail(link, contacto);
  }

  public MailClient() {}

  //para test
  public MailClient(Function<Session, Transport> funcion){
    this.funcion = funcion;
  }

  public void enviarMail(String link, Contacto contacto) {
    notificar(link, contacto.getEmail());
  }

  public void notificar(String link, String mailContacto) {
    MimeMessage mensaje = crearMensaje("Guia de recomendaciones", link, mailContacto);
    if(funcion != null)
      crearMail(mensaje,funcion.apply(session));
    try {
      crearMail(mensaje, session.getTransport());
    } catch(NoSuchProviderException e) {
      throw new NoSePudoEnviarEmail(e.getMessage());
    }

  }

  private Session configurarConexionCorreo(){
    Properties prop = new Properties();
    prop.setProperty("mail.smtp.host", "smtp.gmail.com");
    prop.setProperty("mail.smtp.starttls.enable","true");
    prop.setProperty("mail.smtp.port","587");
    prop.setProperty("mail.smtp.user",mailRemitente);
    prop.setProperty("mail.smtp.auth","true");
    Session session = Session.getDefaultInstance(prop);
    //Es para ver por consola lo que logea el envio de correo
    //true se ve - false no se ve
    //session.setDebug(false);
    return session;
  }

  private MimeMessage crearMensaje(String asunto, String cuerpoMensjae, String mailContacto){
    MimeMessage mensaje = new MimeMessage(session);
    try{
      mensaje.setFrom(new InternetAddress(mailRemitente));
      mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(mailContacto));
      mensaje.setSubject(asunto);
      mensaje.setText(getMensajeBody(cuerpoMensjae), "UTF-8","html");

    } catch (MessagingException e){
      throw new NotificacionCorreoException("Algo salio mal al crear el mensaje del correo");
    }
    return mensaje;
  }

  private String getMensajeBody(String cuerpoMensaje){
    return "<p> Guia de recomendaciones: <p> </br></br> <p>"+ cuerpoMensaje +"</p> </br></br> <p> Saludos";
  }

  private void crearMail(MimeMessage mensaje, Transport t) {
    try {
      t.connect(mailRemitente, passAppRemitente);
      t.sendMessage(mensaje, mensaje.getAllRecipients());
      t.close();
    } catch (MessagingException e) {
      throw new NotificacionCorreoException("Algo salio mal al enviar el correo");
    }
  }

}
