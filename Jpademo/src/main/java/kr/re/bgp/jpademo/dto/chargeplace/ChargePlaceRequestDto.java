package kr.re.bgp.jpademo.dto.chargeplace;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChargePlaceRequestDto extends ChargePlaceCreateDto {
    private Long placeId;
}
