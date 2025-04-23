package kr.re.bgp.jpademo.service.auth;

public enum RoleEnum {
    ADMIN("ADMIN"),
    MANAGER("MANAGER"),
    USER("USER");

    private final String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static RoleEnum fromValue(String value) {
        for (RoleEnum role : RoleEnum.values()) {
            if (role.value.equals(value)) {
                return role;
            }
        }

        throw new IllegalArgumentException(value);
    }

}
