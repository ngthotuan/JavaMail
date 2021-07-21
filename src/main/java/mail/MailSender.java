package mail;

import javax.mail.MessagingException;
import javax.mail.Transport;
import java.util.LinkedList;
import java.util.Queue;

public class MailSender extends Thread {
    private static final Queue<Email> queue = new LinkedList<>();

    static {
        MailSender sender = new MailSender();
        sender.start();
    }

    public static void queue(Email mail) {
        synchronized (queue) {
            queue.add(mail);
            System.out.println("Notify new email to sent");
            queue.notify();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (queue) {
                    Email email = queue.poll();
                    if (email != null) {
                        try {
                            Transport.send(email.getMimeMessage());
                            System.out.printf("Sent email to %s successfully%n", email.getTo());
                        } catch (MessagingException e) {
                            System.err.println("Sent email error");
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Waiting email...");
                        queue.wait();
                    }
                }
            } catch (InterruptedException e) {
                break;
            }

        }
    }
}
