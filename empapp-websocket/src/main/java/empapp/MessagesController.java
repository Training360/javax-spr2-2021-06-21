package empapp;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessagesController {

    @MessageMapping("/messages")
    @SendTo("/topic/employees")
    public MessageDto sendMessage(MessageCommand command) {
        return new MessageDto("Reply: " + command.getContent());
    }
}
