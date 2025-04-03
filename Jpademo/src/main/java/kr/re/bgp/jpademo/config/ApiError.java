package kr.re.bgp.jpademo.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {
    private int errorCode;
    private String message;
}
