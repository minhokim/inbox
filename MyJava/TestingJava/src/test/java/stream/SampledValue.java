package stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SampledValue {
    private Integer connectorId;
    private Integer transactionId;
    private String value;
    private String context;
    private String measurand;
    private String unit;
    private String phase;
}
