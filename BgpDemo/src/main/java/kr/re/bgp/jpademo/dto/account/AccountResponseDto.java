package kr.re.bgp.jpademo.dto.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.re.bgp.jpademo.dto.ResponseDto;
import lombok.Data;

@Data
public class AccountResponseDto implements ResponseDto {
    private Long accountId;
    private String email;
    private String name;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private String created;
}
