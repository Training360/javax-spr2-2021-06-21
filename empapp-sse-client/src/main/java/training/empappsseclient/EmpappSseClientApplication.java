package training.empappsseclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.SseEventSource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Slf4j
public class EmpappSseClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EmpappSseClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CountDownLatch latch = new CountDownLatch(1);

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/api/employees/messages");
		try (SseEventSource source = SseEventSource.target(target)
				//.reconnectingEvery(30, TimeUnit.SECONDS)
				.build()) {
			source.register(
					// JSON unmarshal
					inboundSseEvent -> {String text = inboundSseEvent.readData(MessageServerEvent.class).getText();
						log.info("Text: " + text);
						if ("exit".equals(text)) { latch.countDown();}},
					t -> {throw new IllegalStateException("Error processing sse", t);},
					() -> {log.info("Closing..."); });
			source.open();

			// Várni kell, amíg a szerver le nem zárja a kapcsolatot
			latch.await();
			log.info("Await ends");
		}
		client.close();
	}
}
