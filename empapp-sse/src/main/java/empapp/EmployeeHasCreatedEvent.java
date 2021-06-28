package empapp;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class EmployeeHasCreatedEvent extends ApplicationEvent {

    private String message;

    public EmployeeHasCreatedEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

}
