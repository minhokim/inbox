package kr.re.bgp.jpademo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
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

    private Date created;

    @OneToMany(mappedBy = "place")
    @Builder.Default
    private List<ChargeStation> stations = new ArrayList<>();


}
