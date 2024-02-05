package util;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;


public class StackTest {

    @Test
    public void stack() {
        Stack<String> myStack = new Stack<>();
        myStack.push("Bottom");
        myStack.push("Middle");
        myStack.push("Top");

        Iterator<String> iterator = myStack.iterator();

        assertThat(iterator).toIterable().containsExactly(
                "Bottom",
                "Middle",
                "Top"
        );

        System.out.println(myStack.size());

        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());

        System.out.println(myStack.size());


    }
}
