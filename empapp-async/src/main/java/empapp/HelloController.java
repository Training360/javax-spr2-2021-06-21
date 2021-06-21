package empapp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
@AllArgsConstructor
@Slf4j
public class HelloController {

    private HelloService helloService;

    @GetMapping
    public String sayHello() {
        log.info(Thread.currentThread().getName());
        helloService.sayHello();
        return "Hello World!";
    }
}
