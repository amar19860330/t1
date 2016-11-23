package com.t1.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by amar on 16/9/11.
 * 在google郵箱下通過測試
 */
@Repository
//@Configuration
//@PropertySource("classpath:config.properties")
public class MailUtil {

    //@Value("#{'${email.name}'}")
    @Value("${email.name}")
    private String fromEmail;
    @Value("#{'${email.password}'}")
    private String password;

    private String smtp_host = "smtp.gmail.com";
    private String smtp_port = "587";

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public MailUtil(String fromEmail, String password) {
        this.fromEmail = fromEmail;
        this.password = password;
    }

    public MailUtil(String fromEmail, String password, String smtp_host, String smtp_port) {
        this.fromEmail = fromEmail;
        this.password = password;
        this.smtp_host = smtp_host;
        this.smtp_port = smtp_port;
    }

    public MailUtil() {

    }

    public void senEmail(String toEmail, String emailTitle, String emailContent) {

        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", smtp_host);
            props.put("mail.smtp.port", smtp_port);
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(emailTitle);
            message.setText(emailContent);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void senEmailInThread(String toEmail, String emailTitle, String emailContent) {
        new Thread(() -> senEmail(toEmail, emailTitle, emailContent)).start();
    }


    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmtp_host() {
        return smtp_host;
    }

    public void setSmtp_host(String smtp_host) {
        this.smtp_host = smtp_host;
    }

    public String getSmtp_port() {
        return smtp_port;
    }

    public void setSmtp_port(String smtp_port) {
        this.smtp_port = smtp_port;
    }
}
