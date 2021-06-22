package empapp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageCommand {

    private String content;
}

// { "content": "Hello World!"}
