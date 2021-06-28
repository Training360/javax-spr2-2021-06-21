package empapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ListenerService {

    @EventListener
    public void handleEvent(EmployeeHasCreatedEvent event) {
      log.info("Employee has been created: " + event.getMessage());
    }
}
