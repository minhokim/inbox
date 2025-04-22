package kr.re.bgp.jpademo.dto.account;

import kr.re.bgp.jpademo.dto.BaseDto;
import lombok.Data;

@Data
public class AccountCreateDto implements BaseDto {
    private String name;
    private String email;
    private String password;
}
