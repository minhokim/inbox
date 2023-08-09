package etc;

import org.junit.jupiter.api.Test;

public class StringSplitTest {
    @Test
    public void stringBuilderInsert() {
        String str = "1020111122223333";
        System.out.println("Start : " + str);
        System.out.println(str.substring(0));
        System.out.println(str.substring(4));

        System.out.println(str.substring(0, 4));
        System.out.println(str.substring(4, 8));
        System.out.println(str.substring(8, 12));
        System.out.println(str.substring(12, 16));

        StringBuilder sb = new StringBuilder(str);
        sb.insert(4, "-").insert(9, "-").insert(14, "-");
        System.out.println(sb);
    }
}
