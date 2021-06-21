package empapp;

import org.hibernate.envers.RevisionListener;

public class StubUsernameListener implements RevisionListener {

    @Override
    public void newRevision(Object o) {
        EmployeeRevisionEntity employeeRevisionEntity = (EmployeeRevisionEntity) o;
        employeeRevisionEntity.setUsername("admin");
    }
}
