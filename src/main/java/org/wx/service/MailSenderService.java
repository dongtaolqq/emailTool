package org.wx.service;

import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.wx.config.props.MailProperties;

@Component
@DependsOn(value = { "javaMailSender" })
public class MailSenderService {

    private Logger            logger = LoggerFactory.getLogger(MailSenderService.class);

    @Autowired
    private JavaMailSender    javaMailSender;

    @Autowired
    private VelocityEngine    velocityEngine;

    @Autowired
    private MimeMessage       mimeMessage;

    @Autowired
    private MimeMessageHelper mimeMessageHelper;

    @Value("${velocity.template}")
    private String            template;

    @Autowired
    private MailProperties    mailProperties;

    public void sendWithTemplate(String content, String reciver) {
        String realReciver = StringUtils.EMPTY;
        String mailSuffix = mailProperties.getMailSuffix();
        if (StringUtils.isNotBlank(mailSuffix))
            realReciver = reciver + mailSuffix;
        try {
            mimeMessageHelper.setTo(realReciver);
            mimeMessageHelper.setText(content, true);
            javaMailSender.send(mimeMessage);
            logger.info("##### Send to " + realReciver + " Successfully! #####");
        } catch (Exception e) {
            logger.error("向" + realReciver + "发送邮件时, 出错了! " + e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public String generateMailContent(Map<String, Object> model) {
        String content = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template,
            "UTF-8", model);
        return content;
    }
}
