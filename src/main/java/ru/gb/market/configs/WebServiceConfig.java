package ru.gb.market.configs;

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

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "categorysoap")
    public DefaultWsdl11Definition categoryWsdl11Definition(XsdSchema categorySchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CategoryPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.marketapp.ru/spring/ws/categories");
        wsdl11Definition.setSchema(categorySchema);
        return wsdl11Definition;
    }

    @Bean(name = "productsoap")
    public DefaultWsdl11Definition studentsWsdl11Definition(XsdSchema productSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ProductPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.marketapp.ru/spring/ws/products");
        wsdl11Definition.setSchema(productSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema categorySchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/categories.xsd"));
    }

    @Bean
    public XsdSchema productSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/products.xsd"));
    }
}
