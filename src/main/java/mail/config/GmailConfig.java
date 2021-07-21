package mail.config;

import javax.mail.Session;

public class GmailConfig implements IMailConfig {
    private String username;
    private String password;
    private static final String HOST = "smtp.gmail.com";
    private static final int PORT = 587;
    private final MailConfig mailConfig;

    public GmailConfig(String username, String password) {
        this.username = username;
        this.password = password;
        mailConfig = new MailConfig(HOST, PORT, username, password, true);
    }

    @Override
    public Session getSession() {
        return mailConfig.getSession();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        this.mailConfig.setUsername(username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        this.mailConfig.setPassword(password);
    }
}
