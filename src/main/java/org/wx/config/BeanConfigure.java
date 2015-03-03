package org.wx.config;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.wx.config.props.MailProperties;
import org.wx.config.props.UITextProperties;

@Configuration
@EnableConfigurationProperties({ MailProperties.class, UITextProperties.class })
public class BeanConfigure {

    private static Logger logger = LoggerFactory.getLogger(BeanConfigure.class);

    @Bean
    public VelocityEngineFactoryBean velocityEngineFactory() {
        Properties props = new Properties();
        props.put("resource.loader", "class");
        props.put("class.resource.loader.class",
            "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        VelocityEngineFactoryBean velocityEngineFactoryBean = new VelocityEngineFactoryBean();
        velocityEngineFactoryBean.setVelocityProperties(props);
        return velocityEngineFactoryBean;
    }

    @Bean
    public ResourceBundleMessageSource reloadableResourceBundleMessageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("ui");
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        resourceBundleMessageSource.setUseCodeAsDefaultMessage(true);
        return resourceBundleMessageSource;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        logger.info("Initialize PropertySourcesPlaceholder!!!");
        return new PropertySourcesPlaceholderConfigurer();
    }
}
