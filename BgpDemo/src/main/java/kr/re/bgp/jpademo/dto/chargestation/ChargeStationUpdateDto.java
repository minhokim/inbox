package kr.re.bgp.jpademo.dto.chargestation;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChargeStationUpdateDto extends ChargeStationCreateDto {
    private Long stationId;
}
