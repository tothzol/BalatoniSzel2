package hu.unipannon.mik.balatoniszel.ws;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.spring.JaxWsProxyFactoryBeanDefinitionParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {

    @Value("${backend.url}")
    private String backendUrl;

    @Bean
    public BalatoniSzel client() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setAddress(backendUrl);
        return factory.create(BalatoniSzel.class);
    }
}
