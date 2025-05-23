package kr.re.bgp.jpademo.dto.chargestation;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.re.bgp.jpademo.dto.ResponseDto;
import kr.re.bgp.jpademo.entity.ChargePlace;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ChargeStationResponseDto implements ResponseDto {
    private Long stationId;
    private String stationName;
    private String chargeBoxId;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private Timestamp created;

    private ChargePlace place;
}
