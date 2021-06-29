package empapp;

import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;

@Gateway
@AllArgsConstructor
public class JmsGateway {

    private JmsTemplate jmsTemplate;

    public void sendMessage(String text) {
        jmsTemplate.convertAndSend(new EmployeeHasBeenCreatedDto("Employee has been created: " + text));
    }
}
