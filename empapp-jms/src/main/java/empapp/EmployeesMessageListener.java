package empapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeesMessageListener {

    @JmsListener(destination = "employeesQueue")
    public void handleMessage(EmployeeHasBeenCreatedDto message) {
        log.info("Message has been arrived: " + message.getMessage());
    }

    @JmsListener(destination = "employeesQueue")
    public void handleMessage2(EmployeeHasBeenCreatedDto message) {
        log.info("Message has been arrived 2: " + message.getMessage());
    }
}
