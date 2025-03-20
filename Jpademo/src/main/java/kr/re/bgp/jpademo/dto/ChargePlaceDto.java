package kr.re.bgp.jpademo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.re.bgp.jpademo.entity.ChargeStation;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ChargePlaceDto {
    private Long placeId;
    private String placeName;
    private Double latitude;
    private Double longitude;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private Timestamp created;

    private List<ChargeStation> stations;
}
