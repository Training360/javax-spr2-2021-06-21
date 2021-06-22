package empapp;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class EmployeeHasCreatedEvent extends ApplicationEvent {

    public EmployeeHasCreatedEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    private String message;
}
