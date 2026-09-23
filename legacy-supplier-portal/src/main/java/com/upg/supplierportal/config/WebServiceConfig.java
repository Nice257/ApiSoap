package com.upg.supplierportal.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Configuration Spring-WS :
 *  - MessageDispatcherServlet monte sur /ws/*
 *  - Definition WSDL "mon-service" exposee sur /ws/mon-service.wsdl
 *  - Le schema XSD (classpath:xsd/supplier.xsd) fournit les types du contrat.
 */
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
            ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "mon-service")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema supplierSchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("SupplierPortalPort");
        definition.setLocationUri("/ws");
        definition.setTargetNamespace("http://upg.com/supplierportal");
        definition.setSchema(supplierSchema);
        return definition;
    }

    @Bean
    public XsdSchema supplierSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/supplier.xsd"));
    }
}
