package hu.unipannon.mik.balatoniszel.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class ClientFactory {

    public <T> T getClient(String serverUrl, Class<T> serverApiClass) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setAddress(serverUrl);
        return factory.create(serverApiClass);
    }
}
