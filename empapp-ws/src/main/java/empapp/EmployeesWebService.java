package empapp;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@WebService(serviceName = "EmployeeWebService", targetNamespace = EmployeesWebService.EMPAPP_NAMESPACE)
@Gateway
@AllArgsConstructor
public class EmployeesWebService {

    public static final String EMPAPP_NAMESPACE = "http://training360.com/services/empapp";

    private EmployeesGatewayService employeeService;

    @WebMethod
    @XmlElementWrapper(name = "employees")
    @WebResult(name = "employee")
    public List<EmployeeWdto> listEmployees() {
        return employeeService.listEmployees();
    }
}
