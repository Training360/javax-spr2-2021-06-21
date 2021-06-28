package training.employeeswebsocketclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@Slf4j
public class EmployeesWebsocketClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EmployeesWebsocketClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Started...");
		//WebSocketClient client = new StandardWebSocketClient();
		WebSocketClient client = new SockJsClient(
				Arrays.asList(new WebSocketTransport(new StandardWebSocketClient())));
		WebSocketStompClient stompClient = new WebSocketStompClient(client);
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
		StompSessionHandler sessionHandler = new MessageStompSessionHandler();
		stompClient.connect("ws://localhost:8080/websocket-endpoint", sessionHandler);
		System.out.println("Press any key to exit");
		new Scanner(System.in).nextLine();
	}
}
