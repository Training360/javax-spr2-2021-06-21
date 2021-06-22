package empapp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@AllArgsConstructor
public class MessagesController {

    private SimpMessagingTemplate template;

    @EventListener
    public void handleEvent(EmployeeHasCreatedEvent event) {
        log.info("Event has arrived");
        template.convertAndSend("/topic/employees", new MessageDto(event.getMessage()));
    }

    @MessageMapping("/messages")
    @SendTo("/topic/employees")
    public MessageDto sendMessage(MessageCommand command) {
        return new MessageDto("Reply: " + command.getContent());
    }
}
