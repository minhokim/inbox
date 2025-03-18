package kr.re.bgp.jpademo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "charge_place")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChargePlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;
    private String placeName;
    private Double latitude;
    private Double longitude;

    private Timestamp created;

    @OneToMany(mappedBy = "place")
    @Builder.Default
    private List<ChargeStation> stations = new ArrayList<>();


}
