package empapp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @CachePut(value = "employee", key = "#result.id")
    @CacheEvict(value = "employees", allEntries = true)
    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        Employee employee = new Employee(command.getName());
        ModelMapper modelMapper = new ModelMapper();
        if (command.getAddresses() != null) {
            employee.addAddresses(command.getAddresses().stream().map(a -> modelMapper.map(a, Address.class)).collect(Collectors.toList()));
        }
        employeeRepository.save(employee);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Cacheable("employees")
    public List<EmployeeDto> listEmployees() {
        ModelMapper modelMapper = new ModelMapper();
        return employeeRepository.findAllWithAddresses().stream()
                .map(e -> modelMapper.map(e, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @Cacheable(value = "employee")
    public EmployeeDto findEmployeeById(long id) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(employeeRepository.findByIdWithAddresses(id)
                        .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id)),
                EmployeeDto.class);
    }

//    @Scheduled(fixedRate = 5000)
//    @CacheEvict(value = "employee", allEntries = true)
//    public void evictCache() {
//        log.info("Evicting cache");
//    }

    @Transactional
    @CachePut(value = "employee", key = "#id")
    @CacheEvict(value = "employees", allEntries = true)
    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        Employee employeeToModify = employeeRepository.getById(id);
        employeeToModify.setName(command.getName());
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(employeeToModify, EmployeeDto.class);
    }

    @Caching(evict = {
            @CacheEvict(value = "employee"),
            @CacheEvict(value = "employees", allEntries = true)
    })
    public void deleteEmployee(long id) {
        Employee employee = employeeRepository.findByIdWithAddresses(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }
}
