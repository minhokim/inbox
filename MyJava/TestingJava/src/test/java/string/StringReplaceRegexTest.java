package string;

import org.junit.jupiter.api.Test;

public class StringReplaceRegexTest {

    @Test
    public void replaceTest() {
        String str = "{20,21,22,23,0,1}";
        String resultStr = str.replaceAll("\\{", "");
        System.out.println(resultStr);
    }
}
