package map;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class MapPutTest {
    @Test
    public void putIfAbsent() {
        Map<String, Integer> map = new HashMap<>();
        map.put("John", 5);

        // key가 존재하는 경우 value를 반환한다.
        assertThat(map.putIfAbsent("John", 10)).isEqualTo(5);
        assertThat(map.size()).isEqualTo(1);

        // key가 존재하지 않는 경우 map에 저장하고 null을 반환한다.
        assertThat(map.putIfAbsent("John2", 10)).isNull();
        assertThat(map.size()).isEqualTo(2);
    }

    @Test
    public void computeIfAbsent() {
        Map<String, Integer> map = new HashMap<>();
        map.put("John", 6);

        // key가 존재하는 경우 value를 반환한다.
        assertThat(map.computeIfAbsent("John", key -> key.length())).isEqualTo(6);
        assertThat(map.size()).isEqualTo(1);

        // key가 존재하지 않는 경우 Map에 key와 mappingFunction 람다 함수를 실행한 결과를 저장한다.
        assertThat(map.computeIfAbsent("John2", key -> key.length())).isEqualTo(5);
        assertThat(map.get("John2")).isNotNull();
        assertThat(map.size()).isEqualTo(2);
    }

    @Test
    public void compute() {
        Map<String, Integer> map = new HashMap<>();
        map.put("John", 20);
        map.put("Paul", 30);
        map.put("Peter", 40);

        // key와 remappingFunction을 인자로 받고 람다 함수의 결과로 업데이트 되며,
        // key가 존재하지 않는 경우 NullPointerException 발생
        map.compute("Peter", (k, v) -> v + 50);
        assertThat(map.get("Peter")).isEqualTo(40 + 50);
    }


    @Test
    public void computeIfPresent() {
        Map<String, Integer> map = new HashMap<>();
        map.put("John", 20);
        map.put("Paul", 30);
        map.put("Peter", 40);

        // key와 remappingFunction을 인자로 받고 람다 함수의 결과로 업데이트 되며,
        // key가 존재하지 않는 경우 null을 반환한다.
        map.computeIfPresent("Kelly", (k, v) -> v + 10);
        assertThat(map.get("Kelly")).isNull();
    }

    @Test
    public void computeIfPresentTest() {
        Map<String, Integer> map = new HashMap<>();
        map.put("John", 20);
        map.put("Paul", 30);
        map.put("Peter", 40);
        map.computeIfAbsent("John1", s -> 201);
        assertThat(map.get("John1")).isEqualTo(201);
    }

    @Test
    public void merge() {
        Map<String, Integer> map = new HashMap<>();
        map.put("John", 20);
        map.put("Paul", 30);
        map.put("Peter", 40);

        // key가 존재하는 경우 remappingFunction 람다 함수의 결과로 업데이트.
        map.merge("Peter", 50, (k, v) -> map.get("John") + 10);
        assertThat(map.get("Peter")).isEqualTo(30);

        // key가 존재하는 경우 remappingFunction의 결과가 null이면 map에서 key를 삭제한다.
        map.merge("Peter", 30, (k, v) -> map.get("Nancy"));
        assertThat(map.get("Peter")).isNull();
        assertThat(map.size()).isEqualTo(2);

        // key가 존재하지 않으면 remappingFunction를 실행하지 않고 key, value 추가
        map.merge("Kelly", 50, (k, v) -> map.get("John") + 10);
        assertThat(map.get("Kelly")).isEqualTo(50);
        assertThat(map.size()).isEqualTo(3);
    }

    @Test
    public void getOrDefault() {
        String str = "aagbssdf";
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for (char c : str.toCharArray()) {
            if (map2.containsKey(c)) {
                map2.put(c, map2.get(c) + 1);
            } else {
                map2.put(c, 1);
            }
        }


        for (char c : str.toCharArray()) {
            // key가 존재하는 경우 value 반환
            // key가 존재하지 않는 경우 defaultValue 반환
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }

        assertThat(map1).isEqualTo(map2);
    }

    @Test
    public void removeMap() {
        Map<Integer, Boolean> map = new HashMap<>();
        map.put(0, true);
        map.put(1, false);
        map.put(2, false);

        System.out.println(map.entrySet());

        List<Map.Entry<Integer, Boolean>> resultList = map.entrySet().stream().collect(Collectors.toList());

        System.out.println(resultList);

        resultList = resultList.stream().filter(m -> m.getValue() == false).collect(Collectors.toList());

        System.out.println(resultList);

    }

    @Test
    public void mapSize() {
        Map<String, Map<Integer, Boolean>> testMap = new HashMap<>();
        Map<Integer, Boolean> map = new HashMap<>();
        map.put(0, true);
        map.put(1, false);
        map.put(2, false);
        testMap.put("AA", map);

        System.out.println("map.size() : " + testMap.get("AA").size());
    }
}
