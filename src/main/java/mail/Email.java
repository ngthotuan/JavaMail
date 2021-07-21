package mail;

import mail.config.IMailConfig;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;

public class Email {
    private IMailConfig config;
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private final Multipart content;

    public Email(IMailConfig config) {
        this.config = config;
        this.content = new MimeMultipart();
    }

    public MimeMessage getMimeMessage() throws MessagingException {
        MimeMessage message = new MimeMessage(config.getSession());
        message.setRecipients(Message.RecipientType.TO, to);
        message.setRecipients(Message.RecipientType.CC, cc);
        message.setRecipients(Message.RecipientType.BCC, bcc);
        message.setSubject(subject);
        message.setContent(content);
        return message;
    }

    public void setContent(String content) throws MessagingException {
        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(content, "text/html; charset=utf-8");
        this.content.addBodyPart(bodyPart, 0);
    }

    public void addAttachment(File file) throws MessagingException {
        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setDataHandler(new DataHandler(new FileDataSource(file)));
        bodyPart.setFileName(file.getName());
        content.addBodyPart(bodyPart);
    }

    public void sent() throws MessagingException {
        Transport.send(getMimeMessage());
    }

    public IMailConfig getConfig() {
        return config;
    }

    public void setConfig(IMailConfig config) {
        this.config = config;
    }

    public Multipart getContent() {
        return content;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
