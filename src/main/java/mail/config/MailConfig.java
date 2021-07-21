package mail.config;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class MailConfig implements IMailConfig{
    private String hostName;
    private int port;
    private String username;
    private String password;
    private boolean isTSL;

    public MailConfig(String hostName, int port, String username, String password, boolean isTSL) {
        this.hostName = hostName;
        this.port = port;
        this.username = username;
        this.password = password;
        this.isTSL = isTSL;
    }

    public MailConfig(String hostName, int port, String username, String password) {
        this(hostName, port, username, password, false);
    }

    public Properties getConfig() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", this.hostName);
        if(this.isTSL) {
            props.put("mail.smtp.starttls.enable", "true");
        } else {
            props.put("mail.smtp.socketFactory.port", this.port);
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }
        props.put("mail.smtp.port", this.port);
        return props;
    }

    @Override
    public Session getSession(){
        return Session.getDefaultInstance(getConfig(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }


    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isTSL() {
        return isTSL;
    }

    public void setTSL(boolean TSL) {
        isTSL = TSL;
    }
}
