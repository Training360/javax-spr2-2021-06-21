package empapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class EmployeesApplication
{

	public static void main(String[] args) {
		SpringApplication.run(EmployeesApplication.class, args);
	}

}
