package empapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JmsConfig {

    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper){
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTypeIdPropertyName("_typeId");
        // _typeId=empapp.EmployeeHasBeenCreatedDto


        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put("created", EmployeeHasBeenCreatedDto.class);
        converter.setTypeIdMappings(mappings);
        // _typeId=created

        return converter;
    }
}
