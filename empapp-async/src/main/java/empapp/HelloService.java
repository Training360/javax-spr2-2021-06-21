package empapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HelloService {

    @Async
    public void sayHello() {
        for (int i = 0; i < 10; i++) {
            log.info("Waiting " + i);
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException ie) {
                throw new IllegalArgumentException("Error", ie);
            }
        }
    }
}
