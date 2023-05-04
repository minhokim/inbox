package map;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapGetTest {

    @Test
    public void entrySet() {
        Map<String, String> map = new HashMap<>();
        map.put("apple", "APPLE");
        map.put("orange", "ORANGE");
        map.put("banana", "BANANA");

        // entrySet()
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key : " + entry.getKey() + ", value : " + entry.getValue());
        }
        System.out.println("-------------------------------------------------------");

        // keySet()
        for (String key : map.keySet()) {
            String value = map.get(key);
            System.out.println("key : " + key + ", value : " + value);
        }
        System.out.println("-------------------------------------------------------");

        // entrySet().iterator()
        Iterator<Map.Entry<String, String>> entryIterator = map.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, String> entry = entryIterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("key : " + key + ", value : " + value);
        }
        System.out.println("-------------------------------------------------------");

        // keySet().iterator()
        Iterator<String> keyIterator = map.keySet().iterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            String value = map.get(key);
            System.out.println("key : " + key + ", value : " + value);
        }
        System.out.println("-------------------------------------------------------");

        // Lambda
        map.forEach((key, value) -> {
            System.out.println("key : " + key + ", value : " + value);
        });
        System.out.println("-------------------------------------------------------");

        // Stream
        map.entrySet().stream().forEach(entry -> {
            System.out.println("key : " + entry.getKey() + ", value : " + entry.getValue());
        });
        System.out.println("-------------------------------------------------------");

        // Stream sort asc
        map.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry -> {
            System.out.println("key : " + entry.getKey() + ", value : " + entry.getValue());
        });
        System.out.println("-------------------------------------------------------");

        // Stream sort desc
        map.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())).forEach(entry -> {
            System.out.println("key : " + entry.getKey() + ", value : " + entry.getValue());
        });
        System.out.println("-------------------------------------------------------");

    }
}
