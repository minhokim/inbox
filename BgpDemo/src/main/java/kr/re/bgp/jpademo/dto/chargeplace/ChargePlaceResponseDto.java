package kr.re.bgp.jpademo.dto.chargeplace;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.re.bgp.jpademo.dto.ResponseDto;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ChargePlaceResponseDto implements ResponseDto {
    private Long placeId;
    private String placeName;
    private Double latitude;
    private Double longitude;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private Timestamp created;
}