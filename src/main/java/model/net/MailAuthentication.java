package model.net;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * send email with SSL and authentication.
 * @author skuarch
 */
public class MailAuthentication {

    private String textMessage = null;
    private String subject = null;
    private String from = null;
    private String[] to = null;
    private String toEmails = null;
    private String host = null;
    private int port;
    private Properties props = null;
    private Session session = null;
    private String username = null;
    private String password = null;
    private Message message = null;

    //==========================================================================
    /**
     * constructor.
     * @param host String host
     * @param port int port number
     * @param username String username
     * @param password String password
     * @param subject String subject of email
     * @param textMessage String message
     * @param from String email address
     * @param to String[] recipients
     */
    public MailAuthentication(String host, int port, String username, String password, String subject, String textMessage, String from, String... to) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.subject = subject;
        this.textMessage = textMessage;
        this.from = from;
        this.to = to;
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", this.host);
        props.put("mail.smtp.port", this.port);
    }

    //==========================================================================
    /**
     * send email to recipients.
     * @throws Exception if any exception occurs.
     */
    public void send() throws Exception {

        if (from == null || from.length() < 1) {
            throw new NullPointerException("from is null or empty");
        }

        if (to == null || to.length < 1) {
            throw new NullPointerException("to is null or empty");
        }

        toEmails = arrayToStringComma(to);
        startSession();
        createMessage();
        Transport.send(message);

    }

    //==========================================================================
    private void startSession() {

        try {
            session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
        } catch (Exception e) {
            throw e;
        }

    }

    //==========================================================================
    /**
     * create mime message
     * @throws Exception exception
     */
    private void createMessage() throws Exception {

        try {

            message = new MimeMessage(session);
            message.setContent(textMessage, "text/html; charset=utf-8");
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmails));
            message.setSubject(subject);

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