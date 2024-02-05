package util;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class deque {

    @Test
    public void deque() {
        Deque<String> myDeque = new ArrayDeque<>();
        myDeque.push("Bottom");
        myDeque.push("Middle");
        myDeque.push("Top");

        Iterator<String> iterator = myDeque.iterator();

        assertThat(iterator).toIterable().containsExactly(
                "Top", "Middle", "Bottom"
        );

        myDeque.addLast("AA");






    }
}
