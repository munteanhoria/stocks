package com.devm8.stocks.services.gmail;

import com.devm8.stocks.entities.Alarm;
import com.devm8.stocks.services.MailService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class GmailService implements MailService {

    private String username;
    private String password;
    private Properties props;


    @Override
    public void sendNotification(String email, Alarm alarm) {

        String message = "The stock value " + alarm.getStock() + " was updated. \n";

        sendMail(email, "Stock value was updated", message);
    }

    public GmailService(String username, String password) {
        this.username = username;
        this.password = password;

        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }


    private void sendMail(String to, String subject, String content) {
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

            System.out.println("Mail sent.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
