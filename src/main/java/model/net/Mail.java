package model.net;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author skuarch
 */
public class Mail {

    private final String[] to;
    private final String from;
    private final String host;
    private final int port;

    //==========================================================================
    public Mail(String from, String host, int port, String... to) {
        this.to = to;
        this.from = from;
        this.host = host;
        this.port = port;
    }

    //==========================================================================
    public void send(String subject, String messageText) throws Exception {

        if (to == null || to.length < 1) {
            throw new IllegalArgumentException("to is null or empty");
        }

        if (from == null || from.length() < 1) {
            throw new IllegalArgumentException("from is null or empty");
        }

        if (host == null || host.length() < 1) {
            throw new IllegalArgumentException("host is null or empty");
        }

        if (subject == null || subject.length() < 1) {
            throw new IllegalArgumentException("subject is null or empty");
        }

        if (messageText == null || messageText.length() < 1) {
            throw new IllegalArgumentException("messageText is null or empty");
        }

        Properties properties = null;
        Session session = null;
        MimeMessage message = null;
        String emails = null;

        try {

            //emails separed by ","
            emails = arrayToStringComma(to);
            
            // Get system properties
            properties = System.getProperties();

            // Setup mail server
            properties.setProperty("mail.smtp.host", host);
            properties.setProperty("mail.smtp.port", String.valueOf(port));

            // Get the default Session object.
            session = Session.getDefaultInstance(properties);

            // Create a default MimeMessage object.
            message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emails));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(messageText, "text/html; charset=utf-8");
            message.setContent(messageText, "text/html; charset=utf-8");

            // Send message
            Transport.send(message);

        } catch (Exception e) {
            throw e;
        }

    }
    
    //==========================================================================
    /**
     * convert array in to string separated by commas.
     * @param array String[]
     * @return 
     */
    private String arrayToStringComma(String[] array) {

        if (array == null) {
            throw new IllegalArgumentException("array is null");
        }

        StringBuilder sb = new StringBuilder();

        try {

            for (String string : array) {
                if (sb.length() > 0) {
                    sb.append(',');
                }
                sb.append(string);
            }

        } catch (Exception e) {
            throw e;
        }

        return sb.toString();

    }

}
