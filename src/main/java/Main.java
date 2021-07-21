import mail.Email;
import mail.MailSender;
import mail.config.GmailConfig;
import mail.config.IMailConfig;

import javax.mail.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        // user, password
        String username = "sender@gmail.com";
        String password = "senderpassword";
        // config to, cc, bcc
        String to = "to@exapmle.com";
        String cc = "cc1@exapmle.com,cc2@exapmle.com";

        IMailConfig config = new GmailConfig(username, password);
        try {
            Email email1 = new Email(config);
            email1.setTo(to);
            email1.setContent("Simple email content");
            email1.setSubject("Simple email");
            System.out.println("Email 1 add to queue");
            MailSender.queue(email1);

            Email email2 = new Email(config);
            email2.setTo(to);
            email2.setCc(cc);
            email2.setContent("Simple email content with cc");
            email2.setSubject("Simple email with cc");
            System.out.println("Email 2 add to queue");
            MailSender.queue(email2);

            Email email3 = new Email(config);
            email3.setTo(to);
            email3.setContent("<h1>Hello</h1><br>world");
            email3.setSubject("Email service with attachment");
            email3.addAttachment(new File("hello.txt"));
            email3.addAttachment(new File("hello2.txt"));
            System.out.println("Email 3 add to queue");
            MailSender.queue(email3);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
