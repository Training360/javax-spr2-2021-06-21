package empapp;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
@EnableConfigurationProperties(EmployeesProperties.class)
public class WebServiceConfig {

    @Bean
    public Endpoint employeeEndpoint(Bus bus, EmployeesWebService employeeWebService,
                                     EmployeesProperties properties) {
        EndpointImpl endpoint = new EndpointImpl(bus, employeeWebService);

        String prefix = properties.getUrlPrefix();
        endpoint.setPublishedEndpointUrl(prefix + "/services/employees");

        endpoint.publish("/employees");
        return endpoint;
    }
}
