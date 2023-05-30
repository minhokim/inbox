package clazz;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class InstanceOfTest {

    public class Round {

    }

    public class Ring extends Round {

    }

    @Test
    public void instanceOfTest1() {
        Ring ring = new Ring();
        assertThat(ring instanceof Round);
    }

    public class Circle extends Round implements Shape {

    }

    @Test
    public void instanceOfTest2() {
        Circle circle = new Circle();
        assertThat(circle instanceof Circle);
    }

    @Test
    public void instanceOfTest3() {
        Circle circle = new Circle();
        assertThat(circle instanceof Round);
    }

    @Test
    public void instanceOfTest4() {
        Circle circle = new Circle();
        assertThat(circle instanceof Shape);
    }

    public class Triangle implements Shape {

    }

    /*@Test
    public void instanceOfTest5() {
        Circle circle = new Circle();
        assertFalse(circle instanceof Triangle);
    }*/

    public interface RequestType {

    }

    public class LogStatusNotification implements RequestType {

    }

    @Test
    public void instanceOfTest5() {
        RequestType requestType = new LogStatusNotification();
        assertThat(requestType instanceof LogStatusNotification);
    }




}
