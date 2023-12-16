package string;

import org.apache.commons.text.CaseUtils;
import org.junit.jupiter.api.Test;

public class CamelCase {
    @Test
    public void camelCaseTest() {
        String str = CaseUtils.toCamelCase("camel_case", false, new char[]{'_'});
        System.out.println(str);
    }
}
