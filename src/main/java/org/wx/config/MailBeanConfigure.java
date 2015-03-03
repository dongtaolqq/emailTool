package org.wx.config;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.wx.config.props.MailProperties;

@Configuration
public class MailBeanConfigure {

    private Logger logger = LoggerFactory.getLogger(MailBeanConfigure.class);

    /**
     * @Value("${mail.host}") String host,
       @Value("${mail.username}") String username,
       @Value("${mail.password}") String password,
       @Value("${mail.defaultEncoding}") String defaultEncoding,
       @Value("${mail.needAuth}") short needAuth,
       @Value("${mail.timeout}") long timeout
     * 
     * @return
     */
    @Bean(name = "javaMailSender")
    public JavaMailSender mailSender(MailProperties mailProperties) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", mailProperties.isNeedAuth());
        props.put("mail.smtp.timeout", mailProperties.getTimeout());
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(mailProperties.getHost());
        sender.setUsername(mailProperties.getUsername());
        sender.setPassword(mailProperties.getPassword());
        sender.setDefaultEncoding(mailProperties.getDefaultEncoding());
        sender.setJavaMailProperties(props);
        return sender;
    }

    @Bean
    public MimeMessage mimeMailMessage(JavaMailSenderImpl mailSender) {
        return mailSender.createMimeMessage();
    }

    @Bean
    public MimeMessageHelper MimeMessageHelper(MimeMessage mimeMessage,
                                               MailProperties mailProperties) {
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setFrom(mailProperties.getFrom());
            mimeMessageHelper.setSubject(new String(mailProperties.getSubject().getBytes(
                "ISO-8859-1"), "UTF-8"));
        } catch (MessagingException e) {
            logger.error("初始化MimeMessageHelper出错! " + e.getMessage(), e);
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            logger.error("", e);
        }
        return mimeMessageHelper;
    }
}
