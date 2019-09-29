package com.infoshareacademy.wojownicy.service.emailmanager;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class EmailSenderService {

  private static final String MAIL_TRANSPORT_PROTOCOL = "smtp";
  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private PropertiesLoaderService propertiesLoaderService;

  public void sendMessage(List<String> recipients, String subject)
      throws MessagingException {

    try {
      Session getMailSession = Session
          .getDefaultInstance(propertiesLoaderService.loadMailProperties(), null);

      MimeMessage generateMailMessage = new MimeMessage(getMailSession);

      for (String recipient : recipients) {
        generateMailMessage.addRecipient(RecipientType.TO, new InternetAddress(recipient));
      }
      generateMailMessage.setSubject(subject);
      generateMailMessage.setContent(getReservationMessage(), "text/html");

      Transport transport = getMailSession.getTransport(MAIL_TRANSPORT_PROTOCOL);

      String username = propertiesLoaderService.loadCredentialsProperties()
          .getProperty("user.name");
      String password = propertiesLoaderService.loadCredentialsProperties()
          .getProperty("user.password");
      String server = propertiesLoaderService.loadServerProperties().getProperty("mail.smtp.host");

      logger.info("Email send to {}", username);
      transport.connect(server, username, password);
      transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
      transport.close();
    } catch (IOException e) {
      logger.error(e.getMessage());
    }
  }

  private String getReservationMessage(){
    return  "Rezerwacja ksiazki zakonczona pomyslnie.<br>"
        +"Zapraszamy po odbor w ciagu 48 godzin.";
  }
}
