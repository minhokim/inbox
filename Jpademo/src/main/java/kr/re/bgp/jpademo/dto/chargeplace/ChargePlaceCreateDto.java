package kr.re.bgp.jpademo.dto.chargeplace;

import lombok.Data;

@Data
public class ChargePlaceCreateDto {
    private String placeName;
    private Double latitude;
    private Double longitude;
}
