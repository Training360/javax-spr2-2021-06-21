package empapp;

import lombok.AllArgsConstructor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class SseMessagesController {

    private TaskExecutor taskExecutor;

    @GetMapping("/api/employees/messages")
    public SseEmitter getMessages() {
        SseEmitter emitter = new SseEmitter();

        taskExecutor.execute(() -> sendEvents(emitter));

        return emitter;
    }

    @Async
    public void sendEvents(SseEmitter emitter) {
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                emitter.send(
                        SseEmitter.event()
                            .name("hello-message")
                        .id(UUID.randomUUID().toString())
                        .comment("This is a sample message")
                        .reconnectTime(10_000)
                        .data(new MessageServerEvent("Hello SSE World!"))
                );
            }
        } catch (InterruptedException | IOException ioe) {
            throw new IllegalArgumentException("Can not send", ioe);
        } finally {
            emitter.complete();
        }
    }
}
