package Managers;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail {
    private static final String SRC_MAIL_ADDRESS = "foodonlinemail@gmail.com"; //sends from
    private static final String SRC_MAIL_PASSWORD = "adeqageda";	//user password

    private Properties mailServerProperties;
    private Session getMailSession;
    private MimeMessage generateMailMessage;


    public SendMail(){

    }


    /*
     * sends message to mailAdress from SRC_MAIL_ADDRESS
     * message should be in HTML String format
     */
    public boolean sendEmail(String mailAddress, String message) throws AddressException, MessagingException {

        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailAddress));
        generateMailMessage.setSubject("Confirmation from food-online");
        generateMailMessage.setContent(message, "text/html");

        Transport transport = getMailSession.getTransport("smtp");

        transport.connect("smtp.gmail.com", SRC_MAIL_ADDRESS, SRC_MAIL_PASSWORD);
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
        System.out.println("message sent successfully");
        return true;
    }
}
