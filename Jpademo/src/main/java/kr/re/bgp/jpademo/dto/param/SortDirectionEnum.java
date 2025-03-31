package kr.re.bgp.jpademo.dto.param;

public enum SortDirectionEnum {
    ASC("ASC"),
    DESC("DESC");

    private final String value;

    SortDirectionEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static SortDirectionEnum fromValue(String v) {
        for (SortDirectionEnum c : SortDirectionEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
