package json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class JsonParserTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Test
    public void jsonParser() {
        String jsonMsg = "[2, \"1676250645422-4736AEE3\", \"Heartbeat\", {}]";
        try (JsonParser parser = objectMapper.getFactory().createParser(jsonMsg)) {
            parser.nextToken();

            parser.nextToken();
            int messageTypeNr = parser.getIntValue();

            parser.nextToken();
            String messageId = parser.getText();

            System.out.println("messageId : " + messageId);
        } catch (IOException e) {
            throw new RuntimeException("Deserialization of incoming string failed: " + jsonMsg);
        }

    }
}
