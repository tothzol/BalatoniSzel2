<<<<<<< HEAD
package hu.unipannon.mik.balatoniszel.ws;

import hu.unipannon.mik.balatoniszel.core.Apartman;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
=======
package hu.unipannon.mik.balatoniszel;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
>>>>>>> 29a372930036ad7f49d018af4466c77cface1e33
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@EnableAutoConfiguration
@ImportResource({"classpath:META-INF/cxf/cxf.xml" })
public class ServerConfiguration {

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean<>(new CXFServlet(), "/*");
    }

    @Bean
<<<<<<< HEAD
    public EndpointImpl balatoniSzelService(ApplicationContext applicationContext, Apartman apartman) {
        Bus bus = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        BalatoniSzel implementor = new BalatoniSzelImpl(apartman);
        EndpointImpl endpoint = new EndpointImpl(bus, implementor);
        endpoint.publish("/");
=======
    public EndpointImpl balatoniSzelService(ApplicationContext applicationContext) {
        Bus bus = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        BalatoniSzel implementor = new BalatoniSzelImpl();
        EndpointImpl endpoint = new EndpointImpl(bus, implementor);
        endpoint.publish("/BalatoniSzel");
>>>>>>> 29a372930036ad7f49d018af4466c77cface1e33
        return endpoint;
    }

}
