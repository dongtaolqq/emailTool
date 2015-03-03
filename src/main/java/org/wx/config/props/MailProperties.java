package org.wx.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mail")
public class MailProperties {

    private String  host;
    private String  username;
    private String  password;
    private String  from;
    private String  subject;
    private int     timeout;
    private String  defaultEncoding;
    private boolean needAuth;
    private String  mailSuffix;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getDefaultEncoding() {
        return defaultEncoding;
    }

    public void setDefaultEncoding(String defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }

    public boolean isNeedAuth() {
        return needAuth;
    }

    public void setNeedAuth(boolean needAuth) {
        this.needAuth = needAuth;
    }

    public String getMailSuffix() {
        return mailSuffix;
    }

    public void setMailSuffix(String mailSuffix) {
        this.mailSuffix = mailSuffix;
    }

    @Override
    public String toString() {
        return "MailProperties [host=" + host + ", username=" + username + ", password=" + password
               + ", from=" + from + ", subject=" + subject + ", timeout=" + timeout
               + ", defaultEncoding=" + defaultEncoding + ", needAuth=" + needAuth
               + ", mailSuffix=" + mailSuffix + "]";
    }
}
