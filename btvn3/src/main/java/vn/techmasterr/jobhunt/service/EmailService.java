package vn.techmasterr.jobhunt.service;

import org.springframework.stereotype.Service;
import vn.techmasterr.jobhunt.model.Applicant;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailService {
    public void sendmail(String emailEmployer, Applicant applicant) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("duc0611111@gmail.com", "Duongminhduc@0611");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(emailEmployer, false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailEmployer));
        msg.setSubject("Gửi CV form to Applicant");
        msg.setContent("Gửi CV form to Applicant"
                + applicant.getName()
                + applicant.getEmail()
                + applicant.getPhone()
                + applicant.getSkills()
              , "text/html");
//        msg.setContent(applicant.getName(),"text/html");
//        msg.setContent(applicant.getEmail(),"text/html");
//        msg.setContent(applicant.getPhone(),"text/html");
//        msg.setContent(applicant.getSkills(),"text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Gửi CV form to Applicant"
                + applicant.getName()
                + applicant.getEmail()
                + applicant.getPhone()
                + applicant.getSkills(), "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        msg.setContent(multipart);
        Transport.send(msg);
    }
}
