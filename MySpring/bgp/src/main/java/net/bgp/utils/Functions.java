package net.bgp.utils;

import net.bgp.config.websocket.WebSocketConfig;
import org.apache.commons.lang3.StringUtils;

public class Functions {

    public static String getLastBitFromUrl(final String input) {
        if (StringUtils.isEmpty(input)) {
            return "";
        }

        final String substring = WebSocketConfig.PATH_INFIX;

        int index = input.indexOf(substring);
        if (index == -1) {
            return "";
        } else {
            return input.substring(index + substring.length());
        }
    }

}
