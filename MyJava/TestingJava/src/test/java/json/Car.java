package json;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Car {
    private String color;
    private String type;
//    private Timestamp carTime;
}
