package empapp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
public class SseMessagesController {

    private TaskExecutor taskExecutor;

    private final List<SseEmitter> emitters = new ArrayList<>();

    @EventListener
    public void publish(EmployeeHasCreatedEvent event) {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        for (SseEmitter emitter: emitters) {
            try {
                emitter.send(
                        SseEmitter.event().name("employees-event")
                                .id(UUID.randomUUID().toString())
                                .reconnectTime(10_000)
                                .data(new MessageServerEvent(event.getMessage()))
                );
            }
            catch (Exception ioe) {
                log.warn("Remove emitter", ioe);
                deadEmitters.add(emitter);
            }
        }
        emitters.removeAll(deadEmitters);
    }

    @GetMapping("/api/employees/messages")
    public SseEmitter getMessages() {
        log.info("Connection client");
        SseEmitter emitter = new SseEmitter();
        emitters.add(emitter);
        return emitter;
    }

}
