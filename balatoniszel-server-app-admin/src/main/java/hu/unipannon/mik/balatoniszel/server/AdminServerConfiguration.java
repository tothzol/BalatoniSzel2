package hu.unipannon.mik.balatoniszel.server;

import hu.unipannon.mik.balatoniszel.client.BalatoniSzelAdmin;
import hu.unipannon.mik.balatoniszel.client.BalatoniSzelAdminImpl;
import hu.unipannon.mik.balatoniszel.client.ServerUrls;
import hu.unipannon.mik.balatoniszel.core.Apartman;
import hu.unipannon.mik.balatoniszel.core.GuestRepository;
import hu.unipannon.mik.balatoniszel.core.SessionRepository;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.time.Clock;

@Configuration
@ImportResource({"classpath:META-INF/cxf/cxf.xml" })
public class AdminServerConfiguration {

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean<>(new CXFServlet(), "/*");
    }

    @Bean
    public EndpointImpl balatoniSzelAdminService(ApplicationContext applicationContext,
                                            GuestRepository guestRepository,
                                            SessionRepository sessionRepository,
                                            ServerUrls serverUrls,
                                            Clock clock,
                                            Apartman apartman) {
        Bus          bus         = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        BalatoniSzelAdmin implementor = new BalatoniSzelAdminImpl(apartman, guestRepository, sessionRepository, serverUrls, clock);
        EndpointImpl endpoint    = new EndpointImpl(bus, implementor);
        endpoint.publish("/admin");
        return endpoint;
    }
}
