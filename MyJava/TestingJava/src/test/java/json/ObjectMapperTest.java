package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectMapperTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Test
    public void objectToJson() {
       /* Car car = new Car("yellow", "renault");
        try {
            objectMapper.writeValue(new File("target/car.json"), car);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            String carAsString = objectMapper.writeValueAsString(car);
            System.out.println("objectToJson carAsString : " + carAsString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }*/
    }


    @Test
    public void jsonToObject() {
        String json = "{ \"color\": \"Black\", \"type\":\"BMW\" }";

        try {
            Car car = objectMapper.readValue(json, Car.class);
            System.out.println("jsonToObject car : " + car.toString());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void jsonToJsonNode() {
        String json = "{ \"color\": \"Black\", \"type\":\"FIAT\" }";
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            String color = jsonNode.get("color").asText();
            System.out.println("jsonToJsonNode color : " + color);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void listFromJsonArray() {
        String jsonArrStr = "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";
        try {
            List<Car> listCar = objectMapper.readValue(jsonArrStr, new TypeReference<>(){});
            System.out.println(listCar);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void mapFromJson() {
        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        try {
            Map<String, Object> map = objectMapper.readValue(json, new TypeReference<Map<String,Object>>(){});
            System.out.println("mapFromJson map : " + map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void serialization() {
        String jsonString = "{ \"color\" : \"Black\", \"type\" : \"Fiat\", \"year\" : \"1970\" }";
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        try {
            Car car = objectMapper.readValue(jsonString, Car.class);
            JsonNode jsonNodeRoot = objectMapper.readTree(jsonString);
            JsonNode jsonNodeYear = jsonNodeRoot.get("year");
            String year = jsonNodeYear.asText();
            System.out.println("car : " + car);
            System.out.println("year : " + year);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void mapToObject() {
        Map<String, Object> map = new HashMap<>();
        map.put("color", "white");
        map.put("type", "suv");
        map.put("carTime", "20231031112349");

        Car car = objectMapper.convertValue(map, Car.class);
        System.out.println("car : " + car.toString());

    }

    @Test
    public void strToTimestamp() {
//        String str = "20231031112349";
        String str = "2024-12-18T14:52:59.927302+09:00".replace("T", "");
        String newStr = str.substring(0, str.indexOf("."));

        System.out.println(newStr);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(newStr));
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        System.out.println(timestamp);
    }

    @Test
    public void readValueTreeTest() {
        String jsonString = "{ \"color\" : \"Black\", \"type\" : \"Fiat\", \"year\" : \"1970\" }";

    }

}
