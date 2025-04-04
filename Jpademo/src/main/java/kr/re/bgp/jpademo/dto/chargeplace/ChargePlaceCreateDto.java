package kr.re.bgp.jpademo.dto.chargeplace;

import kr.re.bgp.jpademo.dto.BaseDto;
import lombok.Data;

@Data
public class ChargePlaceCreateDto implements BaseDto {
    private String placeName;
    private Double latitude;
    private Double longitude;
}
