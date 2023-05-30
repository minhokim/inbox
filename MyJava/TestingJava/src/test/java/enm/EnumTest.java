package enm;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EnumTest {

    @Test
    public void getEnum() {
        System.out.println(UploadLogStatus.fromValue("Idle"));
        System.out.println("status : " + UploadLogStatus.fromValue("Idle"));
    }

}
