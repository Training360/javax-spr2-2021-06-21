package empapp;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> listHistoryById(long id) {
        AuditReader reader = AuditReaderFactory.get(entityManager);
        List<Number> revisionNumbers = reader.getRevisions(Employee.class, id);
        log.info("Numbers: " + revisionNumbers);
        return revisionNumbers.stream()
                .map(rev -> reader.find(Employee.class, id, rev))
                .collect(Collectors.toList());
    }
}
