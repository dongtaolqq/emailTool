package org.wx;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.wx.config.BeanConfigure;
import org.wx.config.MailBeanConfigure;
import org.wx.config.UIBeanConfigure;
import org.wx.ui.MainWindow;

@ComponentScan(basePackages = "org.wx")
@Configuration
@PropertySource({ "classpath:mail.properties", "classpath:velocity.properties",
                 "classpath:ui.properties" })
@Import({ BeanConfigure.class, UIBeanConfigure.class, MailBeanConfigure.class })
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @SuppressWarnings("resource")
    public static void main(String[] args) throws UnsupportedEncodingException {
        logger.info("启动程序...");
        ApplicationContext contenxt = new AnnotationConfigApplicationContext(Application.class);
        MainWindow window = contenxt.getBean(MainWindow.class);
        window.start();

    }

}