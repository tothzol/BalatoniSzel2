<<<<<<< HEAD
package hu.unipannon.mik.balatoniszel.ws;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
=======
package hu.unipannon.mik.balatoniszel;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.spring.JaxWsProxyFactoryBeanDefinitionParser;
>>>>>>> 29a372930036ad7f49d018af4466c77cface1e33
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {

<<<<<<< HEAD
    @Value("${backend.url}")
    private String backendUrl;
=======
    @Value("${server.url}")
    private String serverUrl;
>>>>>>> 29a372930036ad7f49d018af4466c77cface1e33

    @Bean
    public BalatoniSzel client() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
<<<<<<< HEAD
        factory.setAddress(backendUrl);
=======
>>>>>>> 29a372930036ad7f49d018af4466c77cface1e33
        return factory.create(BalatoniSzel.class);
    }
}
