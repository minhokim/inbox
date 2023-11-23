package stream;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class DeviceValue {
    private List<MeterValue> meterValue;
}
