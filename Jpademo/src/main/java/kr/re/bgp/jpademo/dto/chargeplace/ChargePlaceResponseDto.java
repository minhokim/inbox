package kr.re.bgp.jpademo.dto.chargeplace;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChargePlaceResponseDto {
    private Long placeId;
    private String placeName;
}