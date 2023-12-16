package stream;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class MeterValue {
    private Timestamp timestamp;
    protected List<SampledValue> sampledValue;
}
