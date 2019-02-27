package hu.unipannon.mik.balatoniszel;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.spring.JaxWsProxyFactoryBeanDefinitionParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {

    @Value("${server.url}")
    private String serverUrl;

    @Bean
    public BalatoniSzel client() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        return factory.create(BalatoniSzel.class);
    }
}
