package stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void flatMapTest() {
        String json = "{\"meterValue\": [ {\"timestamp\": \"2023-11-23T13:56:03Z\",\n" +
                "\"sampledValue\": [\n" +
                "{ \"value\": \"2901.000\", \"context\": \"Sample.Periodic\", \"measurand\": \"Energy.Active.Import.Register\", \"unit\": \"Wh\" },\n" +
                "{ \"value\": \"14.7\", \"context\": \"Sample.Periodic\", \"measurand\": \"Current.Import\", \"unit\": \"A\", \"phase\": \"L1\" },\n" +
                "{ \"value\": \"0\", \"context\": \"Sample.Periodic\", \"measurand\": \"Current.Import\", \"unit\": \"A\", \"phase\": \"L2\" },\n" +
                "{ \"value\": \"0\", \"context\": \"Sample.Periodic\", \"measurand\": \"Current.Import\", \"unit\": \"A\", \"phase\": \"L3\" },\n" +
                "{ \"value\":\"232.300\",\"context\":\"Sample.Periodic\",\"measurand\":\"Voltage\", \"unit\": \"V\", \"phase\":\"L1-N\"} \n" +
                "]}]}";

        try {
            DeviceValue deviceValue = objectMapper.readValue(json, DeviceValue.class);
            List<MeterValue> list = deviceValue.getMeterValue();

            List<SampledValue> sampledValues = list.stream().flatMap(
                    t -> t.getSampledValue().stream().map(
                            k -> SampledValue.builder()
                                    .value(k.getValue())
                                    .context(k.getContext())
                                    .measurand(k.getMeasurand())
                                    .unit(k.getUnit())
                                    .build()
                    )
            ).collect(Collectors.toList());

            System.out.println(sampledValues);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}
