package empapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HelloService {

    @Async
    public void sayHello() {
        log.info(Thread.currentThread().getName());
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

    @Scheduled(fixedRate = 5000)
    public void sayHelloAtFixedRate() {
        log.info(Thread.currentThread().getName());
        log.info("Say hello from scheduler!");
    }
}
