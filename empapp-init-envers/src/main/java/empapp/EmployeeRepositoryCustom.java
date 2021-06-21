package empapp;

import java.util.List;

public interface EmployeeRepositoryCustom {

    List<Employee> listHistoryById(long id);
}
