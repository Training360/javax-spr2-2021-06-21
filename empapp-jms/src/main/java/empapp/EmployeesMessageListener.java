package empapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Service
@Slf4j
public class EmployeesMessageListener {

    @JmsListener(destination = "eventsQueue")
//    public void handleMessage(EmployeeHasBeenCreatedDto message) {
//        log.info("Message has been arrived: " + message.getMessage());
//    }

    public void handleMessage(Message message) throws JMSException {
        log.info("Message has been arrived: " + message);
        log.info(message.getJMSMessageID());
        log.info(Boolean.toString(message.getJMSRedelivered()));
        log.info(Integer.toString(message.getIntProperty("JMSXDeliveryCount")));

        throw new IllegalArgumentException("Invalid message");
    }
}
