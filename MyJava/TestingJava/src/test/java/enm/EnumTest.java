package enm;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EnumTest {

    @Test
    public void getEnum() {
        System.out.println(UploadLogStatus.fromValue("Idle"));
        System.out.println("status : " + UploadLogStatus.fromValue("Idle"));
    }

    @Test
    public void switchTest() {

        switch (UploadLogStatus.fromValue("Idle2")) {
            case IDLE:
                System.out.println("Idle");
                break;
            case NOTDEFINED:
                System.out.println("NotDefined");
                break;
            default:
                System.out.println("default");
        }
    }

    @Test
    public void equalTest() {
        UploadLogStatus _status = UploadLogStatus.IDLE;
        if (UploadLogStatus.IDLE == _status) {
            System.out.println("equal");
        }
    }

    @Test
    public void errorCode() {
        System.out.println(ErrorCode.InternalError);
    }




}
