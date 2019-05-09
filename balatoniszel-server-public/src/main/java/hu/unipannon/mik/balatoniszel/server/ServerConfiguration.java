package hu.unipannon.mik.balatoniszel.server;

import hu.unipannon.mik.balatoniszel.core.Apartman;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:META-INF/cxf/cxf.xml" })
public class ServerConfiguration {

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean<>(new CXFServlet(), "/*");
    }

    @Bean
    public EndpointImpl balatoniSzelPublicService(ApplicationContext applicationContext, Apartman apartman) {
        Bus          bus         = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        BalatoniSzelPublic implementor = new BalatoniSzelPublicImpl();
        EndpointImpl endpoint    = new EndpointImpl(bus, implementor);
        endpoint.publish("/");
        return endpoint;
    }
}
