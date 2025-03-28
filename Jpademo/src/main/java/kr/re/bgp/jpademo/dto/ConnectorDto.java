package kr.re.bgp.jpademo.dto;

import lombok.Data;

@Data
public class ConnectorDto {
    private Long id;
    private Integer connectorId;
    private Long stationId;
}
