package empapp;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService {

    private static final String EMPLOYEES_CREATED_COUNTER_NAME = "employees.created";

    private EmployeeRepository employeeRepository;

    private MeterRegistry meterRegistry;

    private ApplicationEventPublisher publisher;

    @PostConstruct
    public void init() {
        Counter.builder(EMPLOYEES_CREATED_COUNTER_NAME)
                .baseUnit("employees")
                .description("Number of created employees")
                .register(meterRegistry);

    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        log.info("Create employee");
        log.debug("Create employee with name: " + command.getName());
        Employee employee = new Employee(command.getName());
        ModelMapper modelMapper = new ModelMapper();
        if (command.getAddresses() != null) {
            employee.addAddresses(command.getAddresses().stream().map(a -> modelMapper.map(a, Address.class)).collect(Collectors.toList()));
        }
        employeeRepository.save(employee);

        meterRegistry.counter(EMPLOYEES_CREATED_COUNTER_NAME).increment();

        Map<String, Object> data = new HashMap<>();
        data.put("name", command.getName());
        publisher.publishEvent(new AuditApplicationEvent("trainer", "employee-created", data));

        return modelMapper.map(employee, EmployeeDto.class);
    }

    public List<EmployeeDto> listEmployees() {
        ModelMapper modelMapper = new ModelMapper();
        return employeeRepository.findAllWithAddresses().stream()
                .map(e -> modelMapper.map(e, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto findEmployeeById(long id) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(employeeRepository.findByIdWithAddresses(id)
                        .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id)),
                EmployeeDto.class);
    }

    @Transactional
    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        Employee employeeToModify = employeeRepository.getById(id);
        employeeToModify.setName(command.getName());
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(employeeToModify, EmployeeDto.class);
    }

    public void deleteEmployee(long id) {
        Employee employee = employeeRepository.findByIdWithAddresses(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }
}
