package kr.re.bgp.jpademo.dto.chargeplace;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChargePlaceUpdateDto extends ChargePlaceCreateDto {
    private Long placeId;
}
