package kr.re.bgp.jpademo.dto.chargestation;

import kr.re.bgp.jpademo.dto.BaseDto;
import lombok.Data;

@Data
public class ChargeStationCreateDto implements BaseDto {
    private String stationName;
    private String chargeBoxId;
    private Long placeId;
}
