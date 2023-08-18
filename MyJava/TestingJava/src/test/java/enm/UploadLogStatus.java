package enm;

import lombok.Getter;
import lombok.NoArgsConstructor;


public enum UploadLogStatus {
    BAD_MESSAGE("BadMessage"),
    IDLE("Idle"),
    NOT_SUPPORTED_OPERATION("NotSupportedOperation"),
    PERMISSION_DENIED("PermissionDenied"),
    UPLOADED("Uploaded"),
    UPLOAD_FAILURE("UploadFailure"),
    UPLOADING("Uploading"),
    NOTDEFINED("NotDefined");

    private final String value;

    UploadLogStatus(String v) {
        this.value = v;
    }

    public String value() {
        return this.value;
    }



    public static UploadLogStatus fromValue(String v) {
        for (UploadLogStatus status : UploadLogStatus.values()) {
            if (status.value().equals(v)) {
                return status;
            }
        }
        return NOTDEFINED;
//        throw new IllegalArgumentException(v);
    }
}
