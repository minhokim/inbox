package net.bgp.enums;

import lombok.Getter;


@Getter
public enum BgpVersion {
    V_11("bgp1.1"),
    V_12("bgp1.2");

    private final String value;

    BgpVersion(String s) {
        this.value = s;
    }

    public static BgpVersion fromValue(String v) {
        for (BgpVersion b : BgpVersion.values()) {
            if (b.getValue().equals(v)) {
                return b;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
