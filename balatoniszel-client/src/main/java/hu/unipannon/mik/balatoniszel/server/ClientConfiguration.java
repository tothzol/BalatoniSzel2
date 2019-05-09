package hu.unipannon.mik.balatoniszel.server;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

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
